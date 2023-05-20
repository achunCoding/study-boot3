package cn.wycfight.cloud.springboot.starter.cache.core;

@FunctionalInterface
public interface CacheGetIfAbsent<T> {

    /**
     * 执行参数
     * @param param
     */
    void execute(T param);
}
