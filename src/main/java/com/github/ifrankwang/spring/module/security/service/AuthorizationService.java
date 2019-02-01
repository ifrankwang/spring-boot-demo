package com.github.ifrankwang.spring.module.security.service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Frank Wang
 */
public interface AuthorizationService {
    /**
     * 校验请求是否合法
     *
     * @param request  请求体
     * @param response 回应体
     * @param chain    过滤链
     * @throws IOException      读取异常
     * @throws ServletException 容器异常
     */
    void authRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException;
}
