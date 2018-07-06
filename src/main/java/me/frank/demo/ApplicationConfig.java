package me.frank.demo;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
}
