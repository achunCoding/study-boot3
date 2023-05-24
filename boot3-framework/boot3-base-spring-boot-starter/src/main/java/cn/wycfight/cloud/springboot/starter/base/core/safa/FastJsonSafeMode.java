package cn.wycfight.cloud.springboot.starter.base.core.safa;

import org.springframework.beans.factory.InitializingBean;

/**
 * fastjson安全模式，开启后关闭类型隐式传递
 * @author achun
 */
public class FastJsonSafeMode implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty("fastjson2.parser.safeMode", "true");
    }
}
