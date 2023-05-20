package cn.wycfight.cloud.springboot.starter.cache.core;

@FunctionalInterface
public interface CacheLoader<T> {

    /**
     * 加载缓存
     * @return
     */
    T load();
}
