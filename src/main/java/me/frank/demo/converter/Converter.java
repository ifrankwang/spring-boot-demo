package me.frank.demo.converter;

import java.util.List;

/**
 * @author 王明哲
 */
public interface Converter {
    /**
     * 配置转型对象
     *
     * @param configurableModel 转型对象
     */
    void config(ConfigurableModel configurableModel);

    /**
     * 从原对象转换
     *
     * @param source 原对象
     * @return 待转换对象
     */
    Convertible from(Object source);

    /**
     * 从原对象列表转换
     *
     * @param source 原对象列表
     * @return 待转换对象
     */
    ConvertibleList from(List<?> source);
}
