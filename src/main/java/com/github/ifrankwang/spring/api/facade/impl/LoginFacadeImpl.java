package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.dto.modules.security.LoginInfo;
import com.github.ifrankwang.spring.api.facade.LoginFacade;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.InvalidUsernameOrPasswordException;
import com.github.ifrankwang.spring.module.security.exception.LoginInfoExpiredException;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.service.TokenService;
import com.github.ifrankwang.spring.module.security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class LoginFacadeImpl implements LoginFacade {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginFacadeImpl(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String login(LoginInfo loginInfo) throws UserNotFoundException, InvalidUsernameOrPasswordException {
        final UserEntity user = userService.findByEmail(loginInfo.getEmail());
        user.using(passwordEncoder).checkIfPasswordEqualsTo(loginInfo.getPassword())
            .orElseThrow(InvalidUsernameOrPasswordException::new);
        return tokenService.genTokenFor(loginInfo.getEmail());
    }

    @Override
    public String login(String token) throws UserNotFoundException, LoginInfoExpiredException, InsufficientPermissionException {
        final String email = tokenService.getSubjectFrom(token);
        userService.findByEmail(email);
        return tokenService.genTokenFor(email);
    }
}
