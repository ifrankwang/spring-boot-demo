package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.api.dto.security.RequestToken;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import com.github.ifrankwang.spring.module.security.query.AuthorityQuery;
import com.github.ifrankwang.spring.module.security.repo.UserRepo;
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
    private final TokenService tokenService;
    private final UserRepo userRepo;
    private final AuthorityQuery query;

    public AuthorizationServiceImpl(TokenService tokenService, UserRepo userRepo, AuthorityQuery query) {
        this.tokenService = tokenService;
        this.userRepo = userRepo;
        this.query = query;
    }

    @Override
    public void authRequest(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException, UserNotFoundException, InsufficientPermissionException {
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
            throws UserNotFoundException {
        final String email = tokenService.getSubjectFrom(requestToken.getTokenValue());
        final UserEntity user = userRepo.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                                                       query.findAllByUser(user));
    }
}
