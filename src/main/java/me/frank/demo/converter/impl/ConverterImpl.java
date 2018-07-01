package me.frank.demo.converter.impl;

import me.frank.demo.converter.*;
import org.modelmapper.ModelMapper;

import java.util.List;

/**
 * @author 王明哲
 */
public class ConverterImpl implements Converter {
    private final ModelMapper modelMapper;
    private final ConverterUtil converterUtil;

    public ConverterImpl(ModelMapper modelMapper, ConverterUtil converterUtil) {
        this.modelMapper = modelMapper;
        this.converterUtil = converterUtil;
    }

    @Override
    public void config(ConfigurableModel configurableModel) {
        configurableModel.config(modelMapper, converterUtil);
    }

    @Override
    public Convertible from(Object source) {
        return new ConvertibleImpl(modelMapper, source);
    }

    @Override
    public ConvertibleList from(List<?> sources) {
        return new ConvertibleListImpl(modelMapper, sources);
    }
}
