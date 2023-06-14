package cn.wycfight.cloud.client.authorization;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.Assert;

import java.time.Clock;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 * 从授权服务器进行获取授权令牌并创建 OAuth2AuthorizedClient对象
 * 实现OAuth2AuthorizedClientProvider根据不同的授权流程来实现不同的授权方式
 * 当应用程序需要受保护的资源时，它会使用OAuth2AuthorizedClientProvider接口的实现类获取授权客户端，以便访问受保护的资源
 * 其中有多种实现的方式
 * ClientCredentialsOAuth2AuthorizedClientProvider:用于获取使用客户端凭证授权方式的授权客户端
 * RefreshTokenOAuth2AuthorizedClientProvider: 用于获取使用刷新令牌授权方式的授权客户端
 * AuthorizationCodeOAuth2AuthorizedClientProvider：用于获取使用授权码授权方式的授权客户端
 * JwtBearerOAuth2AuthorizedClientProvider：用于获取使用JWT令牌授权方式的授权客户端。
 *
 */
public final class DeviceCodeOAuth2AuthorizedClientProvider implements OAuth2AuthorizedClientProvider {

    private OAuth2AccessTokenResponseClient<OAuth2DeviceGrantRequest> accessTokenResponseClient =
            new OAuth2DeviceAccessTokenResponseClient();

    private Duration clockSkew = Duration.ofSeconds(60);

    private Clock clock = Clock.systemUTC();


    public void setAccessTokenResponseClient(OAuth2AccessTokenResponseClient<OAuth2DeviceGrantRequest> accessTokenResponseClient) {
        this.accessTokenResponseClient = accessTokenResponseClient;
    }


    public void setClockSkew(Duration clockSkew) {
        this.clockSkew = clockSkew;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /**
     * 获取授权令牌，如果授权服务器返回的响应中包含了授权令牌，则创建OAuth2AuthorizedClient对象
     * @param context
     * @return
     */
    @Override
    public OAuth2AuthorizedClient authorize(OAuth2AuthorizationContext context) {
        Assert.notNull(context, "context cannot be null");
        ClientRegistration clientRegistration = context.getClientRegistration();
        if (!AuthorizationGrantType.DEVICE_CODE.equals(clientRegistration.getAuthorizationGrantType())) {
            return null;
        }
        OAuth2AuthorizedClient authorizedClient = context.getAuthorizedClient();
        if (authorizedClient != null && !hasTokenExpired(authorizedClient.getAccessToken())) {
            // If client is already authorized but access token is NOT expired than no
            // need for re-authorization
            return null;
        }
        if (authorizedClient != null && authorizedClient.getRefreshToken() != null) {
            // If client is already authorized but access token is expired and a
            // refresh token is available, delegate to refresh_token.
            return null;
        }
        // *****************************************************************
        // Get device_code set via DefaultOAuth2AuthorizedClientManager#setContextAttributesMapper()
        // *****************************************************************
        String deviceCode = context.getAttribute(OAuth2ParameterNames.DEVICE_CODE);
        // Attempt to authorize the client, which will repeatedly fail until the user grants authorization
        OAuth2DeviceGrantRequest deviceGrantRequest = new OAuth2DeviceGrantRequest(deviceCode, clientRegistration);
        OAuth2AccessTokenResponse tokenResponse = getTokenResponse(clientRegistration, deviceGrantRequest);
        return new OAuth2AuthorizedClient(clientRegistration, context.getPrincipal().getName(),
                tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
    }

    private boolean hasTokenExpired(OAuth2Token token) {
        return this.clock.instant().isAfter(token.getExpiresAt().minus(this.clockSkew));
    }

    private OAuth2AccessTokenResponse getTokenResponse(ClientRegistration clientRegistration,
                                                       OAuth2DeviceGrantRequest deviceGrantRequest) {
        try {
            return this.accessTokenResponseClient.getTokenResponse(deviceGrantRequest);
        } catch (OAuth2AuthorizationException ex) {
            throw new ClientAuthorizationException(ex.getError(), clientRegistration.getRegistrationId(), ex);
        }
    }

    public static Function<OAuth2AuthorizeRequest, Map<String, Object>> deviceCodeContextAttributesMapper() {
        return (authorizeRequest) -> {
            HttpServletRequest request = authorizeRequest.getAttribute(HttpServletRequest.class.getName());
            Assert.notNull(request, "request cannot be null");

            // Obtain device code from request
            String deviceCode = request.getParameter(OAuth2ParameterNames.DEVICE_CODE);
            return (deviceCode != null) ? Collections.singletonMap(OAuth2ParameterNames.DEVICE_CODE, deviceCode) :
                    Collections.emptyMap();
        };
    }
}
