package cn.wycfight.cloud.springboot.starter.cache.core;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

/**
 * 缓存接口
 * @author achun
 */
public interface Cache {
    /**
     * 获取数据
     * @param key
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T get(@NotBlank String key, Class<T> clazz);

    /**
     * 存放数据
     * @param key
     * @param value
     */
    void put(@NotBlank String key, Object value);

    /**
     * 如果 keys 全部不存在，则新增，返回 true，反之 false
     */
    Boolean putIfAllAbsent(@NotNull Collection<String> keys);

    /**
     * 删除数据  true 删除成功，false 删除失败
     * @param key
     * @return
     */
    Boolean delete(@NotBlank String key);

    /**
     * 删除多条keys 返回删除的条数
     * @param keys redis的key
     * @return
     */
    Long delete(@NotNull Collection<String> keys);

    /**
     * 是否存在key
     * @param key
     * @return
     */
    Boolean hasKey(@NotBlank String key);

    /**
     * 获取缓存组件实例
     * @return
     */
    Object getInstance();
}
