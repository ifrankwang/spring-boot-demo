package me.frank.spring.boot.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.frank.spring.boot.demo.entity.AppUser;
import me.frank.spring.boot.demo.service.IJwtService;
import me.frank.spring.boot.demo.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static me.frank.spring.boot.demo.exception.ServiceException.*;
import static me.frank.spring.boot.demo.properties.SecurityConst.HEADER_NAME;

// 登录时会调用的过滤器
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                IJwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {
        AppUser user = null;

        // 获取登录参数
        try {
            user = new ObjectMapper()
                    .readValue(request.getInputStream(), AppUser.class);
        } catch (IOException e) {
            LOG.warn("\n尝试读取请求参数出错！");
            ServletUtil.goError(request, response, INVALID_ARGUMENTS);
            return null;
        }

        // 校验用户名密码
        try {
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            Collections.emptyList()
                    ));
        } catch (InternalAuthenticationServiceException e) {
            LOG.warn("\n用户{}不存在！", user.getUsername());
            ServletUtil.goError(request, response, INVALID_USER);
            return null;
        }
    }

    // 登录校验成功后的操作
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        final String USERNAME = ((AppUser) authResult.getPrincipal()).getUsername();

        LOG.info("\n用户{}登陆校验成功！", USERNAME);

        // 生成token
        final String TOKEN = jwtService.genTokenFor(USERNAME);

        // 回应体中加入相应参数
        response.addHeader(HEADER_NAME, TOKEN);

        chain.doFilter(request, response);
    }

    // 登陆校验失败后的操作
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) {
        LOG.warn("\n登陆校验失败！密码错误！");
        // 校验失败后重定向到登陆API中，返回相应数据
        ServletUtil.goError(request, response, INVALID_PASSWORD);
    }
}
