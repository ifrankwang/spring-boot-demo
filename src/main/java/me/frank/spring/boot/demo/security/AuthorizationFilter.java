package me.frank.spring.boot.demo.security;

import me.frank.spring.boot.demo.entity.AppUser;
import me.frank.spring.boot.demo.exception.ServiceException;
import me.frank.spring.boot.demo.service.IJwtService;
import me.frank.spring.boot.demo.util.ServletUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.frank.spring.boot.demo.exception.ServiceException.INVALID_TOKEN;
import static me.frank.spring.boot.demo.properties.SecurityConst.*;

public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserDetailsService userDetailsService;
    private final IJwtService jwtService;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               UserDetailsService userDetailsService,
                               IJwtService jwtService) {
        super(authenticationManager);
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {
        final String URI = request.getRequestURI();
        final String HEADER = request.getHeader(HEADER_NAME);

        // 没有token时不校验token
        if (StringUtils.isEmpty(HEADER) || !HEADER.startsWith(TOKEN_PREFIX) || URI.contains(AUTH_FAILED_URL)) {
            if (URI.contains(AUTH_FAILED_URL)) {
                LOG.warn("\n转向异常接口！");
            }

            chain.doFilter(request, response);
            return;
        }

        // 解密token
        UsernamePasswordAuthenticationToken token;

        try {
            token = getToken(request);
        } catch (ServiceException e) {
            LOG.warn("\n{}", e.getMessage());
            ServletUtil.goError(request, response, e);
            return;
        }

        // 设置请求权限
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }

    // 解密token
    private UsernamePasswordAuthenticationToken getToken(HttpServletRequest request)
            throws ServiceException {
        final String TOKEN = request.getHeader(HEADER_NAME);

        Asserts.notNull(TOKEN, "Token should not be null, please check your code!");
        LOG.info("\n获取请求中的Token：{}", TOKEN);

        final String USER_NAME = jwtService.getSubjectFrom(TOKEN);

        if (StringUtils.isNotEmpty(USER_NAME)) {
            // 获取用户信息
            LOG.info("\nToken'{}'有效！属于用户：{}", TOKEN, USER_NAME);

            final AppUser USER = (AppUser) userDetailsService.loadUserByUsername(USER_NAME);
            final UsernamePasswordAuthenticationToken AUTH =
                    new UsernamePasswordAuthenticationToken(USER_NAME, USER.getPassword(), USER.getAuthorities());
            AUTH.setDetails(USER);
            return AUTH;
        } else {
            LOG.warn("\n{}不是有效的Token！", TOKEN);
            throw INVALID_TOKEN;
        }
    }
}
