package cn.wycfight.cloud.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;


/**
 * security 客户端 配置
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
    /**
     * 忽略/webjars/assets/检查
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/webjars/**", "/assets/**");
    }

    /**
     * 配置OAuth2.0登陆和注销的方式 配置Http安全性规则和OAuth2.0配置
     * authorizeHttpRequests()表示所有请求需要通过身份验证才能访问， 但/logged-out可以被所有用户访问
     * oauth2Login 配置OAuth 2.0登陆行为，登陆页面为/oauth2/authorization/messaging-client-oidc，其中messaging-client-oidc是OAuth 2.0客户端的注册ID
     * oauth2Client 配置OAuth2.0客户端行为，使用了默认的客户端
     * .logout() 注销行为 重定向到OAuth2.0提供者的注销页面
     * @param http
     * @param clientRegistrationRepository
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/logged-out").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login ->
                        oauth2Login.loginPage("/oauth2/authorization/messaging-client-oidc"))
                .oauth2Client(withDefaults())
                .logout(logout ->
                        logout.logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository)));
        return http.build();
    }

    /**
     * 用户注销后的操作，注销后重定向到指定页面
     * @param clientRegistrationRepository
     * @return
     */
    private LogoutSuccessHandler oidcLogoutSuccessHandler(
            ClientRegistrationRepository clientRegistrationRepository) {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);

        // Set the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logged-out");

        return oidcLogoutSuccessHandler;
    }
}
