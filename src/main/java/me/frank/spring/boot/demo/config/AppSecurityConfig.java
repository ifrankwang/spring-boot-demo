package me.frank.spring.boot.demo.config;

import me.frank.spring.boot.demo.properties.SecurityProperties;
import me.frank.spring.boot.demo.security.AuthenticationFilter;
import me.frank.spring.boot.demo.security.AuthorizationFilter;
import me.frank.spring.boot.demo.service.IJwtService;
import me.frank.spring.boot.demo.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

import static me.frank.spring.boot.demo.exception.ServiceException.INSUFFICIENT_PERMISSION;
import static me.frank.spring.boot.demo.properties.SecurityConst.*;

@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final IJwtService jwtService;

    @Autowired
    public AppSecurityConfig(UserDetailsService userDetailsService,
                             IJwtService jwtService) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // security的相关配置
        http.cors().and().csrf().disable()
            // 身份校验过滤器
            .addFilter(new AuthenticationFilter(authenticationManager(), jwtService))
            // 权限校验过滤器
            .addFilter(new AuthorizationFilter(authenticationManager(), userDetailsService, jwtService))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 在此加入不走权限的API地址
            .antMatchers(NO_AUTH_URL + "/**", LOGIN_URL, AUTH_FAILED_URL)
            .permitAll()
            .anyRequest().authenticated()
            .and()
            // 校验异常处理
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态网页资源不做安全校验
        web.ignoring()
           .antMatchers("/resources/**", "/static/**",
                        "/public/**", "/swagger-ui/**", "/swagger-resources/**",
                        "/v2/api-docs/**", "/*.html", "/**/*.html",
                        "/**/*.css", "/**/*.js", "/**/*.png",
                        "/**/*.jpg", "/**/*.gif", "/**/*.svg",
                        "/**/*.ico", "/**/*.ttf", "/**/*.woff",
                        "/**/*.otf");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 获取用户信息的服务
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        // 校验异常转向处理
        return (request, response, e) -> ServletUtil.goError(request, response, INSUFFICIENT_PERMISSION);
    }
}
