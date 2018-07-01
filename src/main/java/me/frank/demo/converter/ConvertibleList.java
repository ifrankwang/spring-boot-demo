package me.frank.demo.converter;

import java.util.List;

/**
 * @author 王明哲
 */
public interface ConvertibleList {
    /**
     * 转换成目标类对象
     *
     * @param targetClass 目标类
     * @param <T>         目标类型
     * @return 目标类对象
     */
    <T> List<T> to(Class<T> targetClass);
}
