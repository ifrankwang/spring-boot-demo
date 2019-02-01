package com.github.ifrankwang.spring.config;

import com.github.ifrankwang.converter.core.Converter;
import com.github.ifrankwang.converter.core.impl.ConverterImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.convention.MatchingStrategies.LOOSE;

/**
 * @author Frank Wang
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public Converter converter(@Autowired ModelMapper modelMapper) {
        final Converter converter = new ConverterImpl(modelMapper);
        configConvertible(converter);
        return converter;
    }

    private void configConvertible(Converter converter) {
        // TODO 配置DTO
    }

    /**
     * DTO Entity 之间的转型工具
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
}
