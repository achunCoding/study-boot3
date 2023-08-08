package cn.wycfight.cloud.user.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname UserProviderApplication
 * @Description 用户中台服务提供者
 * @Date 2023/8/8 15:58
 * @Created by achun
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(UserProviderApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
