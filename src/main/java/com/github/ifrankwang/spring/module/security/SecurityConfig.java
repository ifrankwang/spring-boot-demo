package com.github.ifrankwang.spring.module.security;

import com.github.ifrankwang.spring.module.security.filter.AuthorizationFilter;
import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import com.github.ifrankwang.spring.module.security.properties.TokenProperties;
import com.github.ifrankwang.spring.module.security.service.AuthorizationService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 应用安全权限相关配置
 *
 * @author Frank Wang
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthorizationService authorizationService;

    public SecurityConfig(@Lazy AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Bean
    @ConfigurationProperties(prefix = "token")
    public TokenProperties tokenProperties() {
        return new TokenProperties();
    }

    /**
     * 安全过滤相关配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .addFilter(new AuthorizationFilter(authenticationManager(), authorizationService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    /**
     * 不做安全校验配置
     */
    @Override
    public void configure(WebSecurity web) {
        final String regexFix = "/**";
        web.ignoring()
           .antMatchers("/resources/**", "/static/**", "/public/**",
                        "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**",
                        "/webjars/**", "/*.html", regexFix + SecurityConst.NO_AUTH_URL + regexFix,
                        SecurityConst.LOGIN_URL,
                        SecurityConst.REGISTER_URL);
    }

    /**
     * 校验异常转向处理
     */
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher(SecurityConst.AUTH_FAILED_URL);
                dispatcher.forward(request, response);
            } catch (ServletException | IOException forwardException) {
                forwardException.printStackTrace();
            }
        };
    }

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
