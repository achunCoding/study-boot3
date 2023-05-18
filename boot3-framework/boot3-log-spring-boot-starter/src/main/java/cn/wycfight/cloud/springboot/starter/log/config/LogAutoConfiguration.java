package cn.wycfight.cloud.springboot.starter.log.config;

import cn.wycfight.cloud.springboot.starter.log.core.aspect.MLogPrintAspect;
import org.springframework.context.annotation.Bean;

/**
 * 日志自动装配
 */
public class LogAutoConfiguration {


    @Bean
    public MLogPrintAspect mLogPrintAspect() {
        return new MLogPrintAspect();
    }
}
