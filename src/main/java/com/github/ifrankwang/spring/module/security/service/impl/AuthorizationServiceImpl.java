package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.api.dto.security.RequestToken;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.factory.UserFactory;
import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import com.github.ifrankwang.spring.module.security.service.AuthorizationService;
import com.github.ifrankwang.spring.module.security.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.ifrankwang.spring.api.dto.security.RequestToken.from;

/**
 * @author Frank Wang
 */
@Service
public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserFactory userFactory;
    private final TokenService tokenService;

    public AuthorizationServiceImpl(UserFactory userFactory, TokenService tokenService) {
        this.userFactory = userFactory;
        this.tokenService = tokenService;
    }

    @Override
    public void authRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException, InsufficientPermissionException {
        final String uri = request.getRequestURI();
        final RequestToken requestToken = from(request.getHeader(SecurityConst.HEADER_NAME));

        // 没有token时不校验token
        if (requestToken.isNotValidToken() || uri.contains(SecurityConst.AUTH_FAILED_URL)) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authToken = getAuthTokenFromRequestToken(requestToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthTokenFromRequestToken(RequestToken requestToken)
            throws InsufficientPermissionException {
        try {
            final String email = tokenService.getSubjectFrom(requestToken.getTokenValue());
            final UserEntity user = userFactory.getByEmail(email);
            return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), user.getAuthorities());
        } catch (UserNotFoundException e) {
            throw new InsufficientPermissionException();
        }
    }
}
