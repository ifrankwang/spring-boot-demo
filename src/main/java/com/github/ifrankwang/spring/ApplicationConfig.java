package com.github.ifrankwang.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 项目配置类
 *
 * @author Frank Wang
 */
@EnableAspectJAutoProxy
@EntityScan("com.github.ifrankwang.spring.module.*.entity")
@SpringBootApplication()
public class ApplicationConfig {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}
