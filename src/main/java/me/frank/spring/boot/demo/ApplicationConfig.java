package me.frank.spring.boot.demo;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import static me.frank.spring.boot.demo.properties.SecurityConst.HEADER_NAME;

// 另外需要引入的properties文件
//@PropertySources({@PropertySource("classpath:others.properties")})
// 切面方法自动代理
@EnableAspectJAutoProxy
// 需要扫描的数据库模型类所在的包
@EntityScan("me.frank.spring.boot.demo.entity")
// MyBatis *Mapper.java 文件扫描
@MapperScan({"me.frank.spring.boot.demo.mapper"})
@SpringBootApplication
public class ApplicationConfig {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }

    // hikari数据源的配置，根据资源文件前缀自动加入参数
    @Bean
    @ConfigurationProperties(prefix = "hikari.datasource")
    public HikariDataSource dataSource() {
        return (HikariDataSource) DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    // 解决跨域问题
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(true)
                        .allowedOrigins("*")
                        .allowedHeaders(HEADER_NAME);
            }
        };
    }
}
