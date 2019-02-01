package com.github.ifrankwang.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Frank Wang
 */
@Configuration
public class DatasourceConfig {
    /**
     * 数据源配置
     */
    @Bean
    @ConfigurationProperties(prefix = "hikari.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
}
