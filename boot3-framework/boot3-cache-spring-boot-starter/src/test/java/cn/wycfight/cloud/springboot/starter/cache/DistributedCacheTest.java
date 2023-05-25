package cn.wycfight.cloud.springboot.starter.cache;

import cn.wycfight.cloud.springboot.starter.cache.core.DistributedCache;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class DistributedCacheTest {

    private DistributedCache distributedCache;

    @BeforeEach
    public void before() {
        ConfigurableApplicationContext context = SpringApplication.run(DistributedCacheTest.class);
        distributedCache = context.getBean(DistributedCache.class);
    }

    @Test
    public void assertSafePut() {
        distributedCache.safePut("test", "test_value", 50000L, null);
        String actual = distributedCache.get("test", String.class);
        Assertions.assertEquals(actual, "test_value");
    }


    @Test
    public void assertSecureGet() {
        distributedCache.safeGet("test", String.class, () -> "test_value", 50000L);
        String actual = distributedCache.get("test", String.class);
        Assertions.assertEquals(actual, "test_value");
    }


    @Test
    public void assertBloomFilterSecureGet() {
        for (int i = 0; i < 2; i++) {
            distributedCache.safeGet("test", String.class, () -> "", 5000L);
        }
    }


    @Test
    public void assertPutIfAllAbsent() {
        List<String> keys = Lists.newArrayList("name", "age");
        Boolean result = distributedCache.putIfAllAbsent(keys);
        Assertions.assertTrue(result);
        keys.forEach(each -> {
            String name = distributedCache.get("name", String.class);
            Assertions.assertEquals(name, "default");
        });
        Boolean resultFalse = distributedCache.putIfAllAbsent(keys);
        Assertions.assertFalse(resultFalse);
    }



}
