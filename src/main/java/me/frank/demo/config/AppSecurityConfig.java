package me.frank.demo.config;

import me.frank.demo.properties.JwtProperties;
import me.frank.demo.security.AuthorizationFilter;
import me.frank.demo.service.JwtService;
import me.frank.demo.service.UserService;
import me.frank.demo.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import static me.frank.demo.exception.ServiceException.INSUFFICIENT_PERMISSION;
import static me.frank.demo.properties.SecurityConst.*;

/**
 * 应用安全权限相关配置
 *
 * @author 王明哲
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(JwtProperties.class)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public AppSecurityConfig(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    /**
     * 安全过滤相关配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .addFilter(new AuthorizationFilter(authenticationManager(), userService, jwtService))
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
                        "/webjars/**", "/*.html", regexFix + NO_AUTH_URL + regexFix,
                        API_PREFIX + LOGIN_URL, API_PREFIX + REGISTER_URL);
    }

    /**
     * 校验异常转向处理
     */
    @Bean
    AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> ServletUtil.goError(request, response, INSUFFICIENT_PERMISSION);
    }
}
