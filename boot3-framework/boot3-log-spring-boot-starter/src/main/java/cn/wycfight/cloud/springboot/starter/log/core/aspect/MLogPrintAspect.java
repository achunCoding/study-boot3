package cn.wycfight.cloud.springboot.starter.log.core.aspect;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.SystemClock;
import cn.wycfight.cloud.springboot.starter.log.core.annotation.MLog;
import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Method;
import java.util.Optional;

@Aspect
public class MLogPrintAspect {

    /**
     * 当使用MLog使用注解标记的类
     */
    @Pointcut("@within(cn.wycfight.cloud.springboot.starter.log.core.annotation.MLog) ||" +
            " @annotation(cn.wycfight.cloud.springboot.starter.log.core.annotation.MLog)")
    private void logPointCut() {}


    @Around("logPointCut()")
    public Object prictMLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = SystemClock.now();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Logger log = LoggerFactory.getLogger(methodSignature.getDeclaringType());
        String beginTime = DateUtil.now();
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            Method targetMethod = joinPoint.getTarget().getClass().getDeclaredMethod(methodSignature.getName(), methodSignature.getMethod().getParameterTypes());
            MLog logAnnotation = Optional.ofNullable(targetMethod.getAnnotation(MLog.class)).orElse(joinPoint.getTarget().getClass().getAnnotation(MLog.class));
            if (logAnnotation != null) {
                MLogPrint logPrint = new MLogPrint();
                logPrint.setBeginTime(beginTime);
                if (logAnnotation.input()) {
                    logPrint.setInputParams(buildInput(joinPoint));
                }
                if (logAnnotation.output()) {
                    logPrint.setOutputParams(result);
                }
                String methodType = "", requestURI = "";
                try {
                    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    methodType = servletRequestAttributes.getRequest().getMethod();
                    requestURI = servletRequestAttributes.getRequest().getRequestURI();
                } catch (Exception ignored) {
                }
                log.info("[{}] {}, executeTime: {}ms, info: {}", methodType, requestURI, SystemClock.now() - startTime, JSON.toJSONString(logPrint));
            }
        }
        return result;

    }



    private Object[] buildInput(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object[] printArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if ((args[i] instanceof HttpServletRequest) || args[i] instanceof HttpServletResponse) {
                continue;
            }
            if (args[i] instanceof byte[]) {
                printArgs[i] = "byte array";
            } else if (args[i] instanceof MultipartFile) {
                printArgs[i] = "file";
            } else {
                printArgs[i] = args[i];
            }
        }
        return printArgs;
    }


    @Data
    private static class MLogPrint {

        private String beginTime;

        private Object[] inputParams;

        private Object outputParams;
    }

}
