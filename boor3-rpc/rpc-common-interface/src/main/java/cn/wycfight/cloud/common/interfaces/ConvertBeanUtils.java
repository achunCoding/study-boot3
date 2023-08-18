package cn.wycfight.cloud.common.interfaces;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ConvertBeanUtils
 * @Description TODO
 * @Author achun
 * @Date 2023/8/18 7:48
 * @Version 1.0
 */
public class ConvertBeanUtils {
    private ConvertBeanUtils() {}


    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T t  = newInstance(targetClass);
        BeanUtils.copyProperties(source, t);
        return t;
    }

    public  static <K, T> List<T> convertList(List<K> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        }
        List targetList = new ArrayList((int) (sourceList.size()/0.75) + 1);
        for (K k : sourceList) {
            targetList.add(convert(k, targetClass));
        }
        return targetList;
    }


    private static <T> T newInstance(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (Exception e) {
            throw new BeanInstantiationException(targetClass, "instantiation error", e);
        }
    }


}
