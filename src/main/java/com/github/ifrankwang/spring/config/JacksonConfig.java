package com.github.ifrankwang.spring.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * Jackson配置类
 *
 * @author Frank Wang
 */
@Configuration
public class JacksonConfig {

    @Bean
    public MappingJackson2HttpMessageConverter objectMapper() {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        final ObjectMapper objectMapper = configObjectMapper();
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    private ObjectMapper configObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
