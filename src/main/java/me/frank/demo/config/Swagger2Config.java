package me.frank.demo.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Lists.newArrayList;
import static me.frank.demo.properties.SecurityConst.AUTH_FAILED_URL;
import static me.frank.demo.properties.SecurityConst.HEADER_NAME;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * Swagger2的配置类
 *
 * @author 王明哲
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    private final String API_KEY_NAME = "token";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()))
                .select()
                .paths(paths())
                .apis(withClassAnnotation(Api.class))
                .build();
    }

    /**
     * 获取API的基本信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot 模板服务端")
                .description("基于Spring Boot开发的服务端")
                .version("1.0")
                .build();
    }

    /**
     * 需要展示或隐藏的API路径
     */
    @SuppressWarnings("all")
    private Predicate<String> paths() {
        return and(regex("/.*"),
                   not(regex("/application.*")),
                   not(regex(AUTH_FAILED_URL)));
    }

    /**
     * 权限token设置
     */
    private ApiKey apiKey() {
        return new ApiKey(API_KEY_NAME, HEADER_NAME, "header");
    }

    /**
     * 需要使用token的API配置
     */
    @SuppressWarnings("all")
    private SecurityContext securityContext() {
        final String SUFFIX = "/.*";
        return SecurityContext.builder()
                              .securityReferences(defaultAuth())
                              .forPaths(and(regex("/.*"),
                                            not(regex(".*/no-auth/.*")),
                                            not(regex(".*/login")),
                                            not(regex(".*/register"))))
                              .build();
    }

    /**
     * 权限范围配置
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference(API_KEY_NAME, authorizationScopes));
    }
}
