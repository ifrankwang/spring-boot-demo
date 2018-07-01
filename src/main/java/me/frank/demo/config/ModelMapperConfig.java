package me.frank.demo.config;

import me.frank.demo.converter.Converter;
import me.frank.demo.converter.ConverterUtil;
import me.frank.demo.converter.impl.ConverterImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

/**
 * @author 王明哲
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public Converter converter() {
        final Converter converter = new ConverterImpl(modelMapper(), converterUtil());
        configConvertible(converter);
        return converter;
    }

    private void configConvertible(Converter converter) {
        // TODO 配置DTO
    }

    @Bean
    public ConverterUtil converterUtil() {
        return new ConverterUtil();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        return modelMapper;
    }
}
