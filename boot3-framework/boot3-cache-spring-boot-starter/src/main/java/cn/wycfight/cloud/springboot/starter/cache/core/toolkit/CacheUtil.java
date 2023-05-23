package cn.wycfight.cloud.springboot.starter.cache.core.toolkit;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 缓存工具类
 * @author achun
 */
public class CacheUtil {

    private static final String SPLICING_OPERATOR = "_";

    private CacheUtil() {}


    public static String buildKey(String... keys) {
        Stream.of(keys).forEach(each ->
                Optional.ofNullable(Strings.emptyToNull(each))
                        .orElseThrow(() -> new RuntimeException("构建缓存 key 不能为空")));
        return Joiner.on(SPLICING_OPERATOR).join(keys);
    }

    public static boolean isNullorEmpty(Object cacheValue) {
        return cacheValue == null || (cacheValue instanceof String && Strings.isNullOrEmpty((String) cacheValue));
    }
}
