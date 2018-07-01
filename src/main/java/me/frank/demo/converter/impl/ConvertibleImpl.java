package me.frank.demo.converter.impl;

import me.frank.demo.converter.Convertible;
import org.modelmapper.ModelMapper;

/**
 * @author 王明哲
 */
public class ConvertibleImpl implements Convertible {
    private final ModelMapper modelMapper;
    private final Object source;

    ConvertibleImpl(ModelMapper modelMapper, Object source) {
        this.modelMapper = modelMapper;
        this.source = source;
    }

    @Override
    public <T> T to(Class<T> targetClass) {
        return modelMapper.map(source, targetClass);
    }
}
