package cn.wycfight.cloud.resource.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {


    /**
     * 配置Spring Security Filter 过滤链  http.securityMatcher 表示只有/messages/** 请求匹配才会进入此过滤连
     * authorizeHttpRequests 表示只有message.read权限才能访问 仅对/messages/**下路径有效
     * oauth2ResourceServer 配置Oauth2资源服务器，使用JWT令牌进行身份认证和授权
     * http.build() 方法将创建一个 SecurityFilterChain Bean，并返回它 以保护WEB资源
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/messages/**")
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.
                        requestMatchers("/messages/**").hasAuthority("SCOPE_message.read"))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        return http.build();
    }


}
