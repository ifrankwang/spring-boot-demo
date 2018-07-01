package me.frank.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static me.frank.demo.properties.SecurityConst.HEADER_NAME;

/**
 * @author 王明哲
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
                        .allowedHeaders(HEADER_NAME, "Content-Type", "X-Requested-With", "accept",
                                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers");
            }
        };
    }
}
