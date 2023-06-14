package cn.wycfight.cloud.client.authorization;

import org.springframework.security.oauth2.client.endpoint.AbstractOAuth2AuthorizationGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.util.Assert;

/**
 * OAuth 2.0设备请求授权，包含deviceCode,用于获取访问令牌(accessToken)
 *
 */
public final class OAuth2DeviceGrantRequest extends AbstractOAuth2AuthorizationGrantRequest {

    private final String deviceCode;


    public OAuth2DeviceGrantRequest(String deviceCode, ClientRegistration clientRegistration) {
        super(AuthorizationGrantType.DEVICE_CODE, clientRegistration);
        Assert.hasText(deviceCode, "deviceCode cannot be empty");
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }
}
