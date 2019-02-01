package com.github.ifrankwang.spring.config;

import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Frank Wang
 */
@Configuration
public class WebMvcConfig {

    /**
     * 解决跨域问题
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "OPTIONS", "PUT", "PATCH", "DELETE")
                        .allowedHeaders(SecurityConst.HEADER_NAME, "Content-Type", "X-Requested-With", "accept",
                                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers");
            }
        };
    }
}
