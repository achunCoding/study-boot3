package cn.wycfight.cloud.springboot.starter.cache.core;

@FunctionalInterface
public interface CacheGetFilter<T> {

    /**
     * 缓存过滤
     * @param param 请求参数
     * @return
     */
    boolean filter(T param);
}
