package me.frank.demo.converter;

import org.modelmapper.ModelMapper;

/**
 * @author 王明哲
 */
public interface ConfigurableModel {
    /**
     * ModelMapper转型配置
     *
     * @param modelMapper   ModelMapper
     * @param converterUtil 转型配置工具
     */
    void config(ModelMapper modelMapper, ConverterUtil converterUtil);
}
