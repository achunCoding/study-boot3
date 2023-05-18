package cn.wycfight.cloud.springboot.starter.log.core.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示在运行时执行， 只能加在类上或者方法上
 * @author achun
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MLog {
    /**
     * 打印入参参数， true：进行打印， false：不打印 ; 默认是true
     * @return
     */
    boolean input() default true;


    /**
     * 打印出参参数，true： 进行打印， false： 不打印； 默认是true
     * @return
     */
    boolean output() default true;
}
