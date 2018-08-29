package me.frank.demo;

import com.zaxxer.hikari.HikariDataSource;
import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static me.frank.demo.properties.SecurityConst.HEADER_NAME;
import static org.modelmapper.convention.MatchingStrategies.LOOSE;

/**
 * 项目配置类
 *
 * @author 王明哲
 */
@EnableAspectJAutoProxy
@EntityScan("me.frank.demo.entity")
@MapperScan({"me.frank.demo.mapper"})
@SpringBootApplication()
public class ApplicationConfig {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }

    /**
     * 数据源配置
     */
    @Bean
    @ConfigurationProperties(prefix = "hikari.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create()
                                .type(HikariDataSource.class)
                                .build();
    }

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
                        .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                        .allowedHeaders(HEADER_NAME, "Content-Type", "X-Requested-With", "accept",
                                        "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers");
            }
        };
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

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
