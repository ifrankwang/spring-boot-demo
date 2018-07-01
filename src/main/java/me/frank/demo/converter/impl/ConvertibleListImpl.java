package me.frank.demo.converter.impl;

import me.frank.demo.converter.ConvertibleList;
import org.modelmapper.ModelMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author 王明哲
 */
public class ConvertibleListImpl implements ConvertibleList {
    private final ModelMapper modelMapper;
    private final List<?> sources;

    ConvertibleListImpl(ModelMapper modelMapper, List<?> sources) {
        this.modelMapper = modelMapper;
        this.sources = sources;
    }

    @Override
    public <T> List<T> to(Class<T> targetClass) {
        return sources.stream()
                      .map(source -> modelMapper.map(source, targetClass))
                      .collect(toList());
    }
}
