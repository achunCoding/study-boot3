package cn.wycfight.cloud.springboot.starter.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * 分布式缓存配置
 * @author achun
 */
@Data
@ConfigurationProperties(prefix = RedisDistributedProperties.PREFIX)
public class RedisDistributedProperties {

    public static final String PREFIX = "boot3.cache.redis";
    /**
     * key前缀
     */
    private String prefix = "";
    /**
     * key前缀字符集
     */
    private String prefixCharset = "UTF-8";

    /**
     * 超时时间
     */
    private Long valueTimeout = 10000L;

    /**
     * 时间单位
     */
    private TimeUnit valueTimeUnit = TimeUnit.MILLISECONDS;


}
