package com.github.ifrankwang.spring.module.security.filter;

import com.github.ifrankwang.spring.module.security.service.AuthorizationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Token校验过滤器
 *
 * @author Frank Wang
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final AuthorizationService authorizationService;

    public AuthorizationFilter(AuthenticationManager authenticationManager, AuthorizationService authorizationService) {
        super(authenticationManager);
        this.authorizationService = authorizationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        authorizationService.authRequest(request, response, chain);
    }
}
