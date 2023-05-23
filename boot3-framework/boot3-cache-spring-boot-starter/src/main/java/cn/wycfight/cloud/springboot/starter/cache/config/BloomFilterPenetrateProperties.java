package cn.wycfight.cloud.springboot.starter.cache.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = BloomFilterPenetrateProperties.PREFIX)
public class BloomFilterPenetrateProperties {

    public static final String PREFIX = "boot3.cache.redis.bloom-filter.default";

    /**
     * 布隆过滤器实例前缀名称
     */
    private String name = "cache_penetration_bloom_filter";

    /**
     * 每个元素预期插入量
     */
    private Long expectedInsertions = 60000L;

    /**
     * 预期错误概率
     */
    private Double falseProbability = 0.03D;
}
