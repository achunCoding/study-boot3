package cn.wycfight.cloud.api;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ApiWebApplication
 * @Description TODO
 * @Author achun
 * @Date 2023/8/8 22:39
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
public class ApiWebApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApiWebApplication.class);
        springApplication.setWebApplicationType(WebApplicationType.SERVLET);
        springApplication.run(args);
    }
}
