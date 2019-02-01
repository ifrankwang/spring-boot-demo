package com.github.ifrankwang.spring.api.service.impl;

import com.github.ifrankwang.spring.api.dto.security.LoginInfo;
import com.github.ifrankwang.spring.api.service.LoginService;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.InvalidUsernameOrPasswordException;
import com.github.ifrankwang.spring.module.security.factory.UserFactory;
import com.github.ifrankwang.spring.module.security.service.TokenService;
import com.github.ifrankwang.utils.misc.Checkable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final UserFactory userFactory;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginServiceImpl(UserFactory userFactory, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userFactory = userFactory;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public String login(LoginInfo loginInfo) {
        this.loginCheck(loginInfo).orElseThrow(InvalidUsernameOrPasswordException::new);
        return tokenService.genTokenFor(loginInfo.getEmail());
    }

    @Override
    public String login(String token) {
        final String email = tokenService.getSubjectFrom(token);
        userFactory.getByEmail(email);
        return tokenService.genTokenFor(email);
    }

    private Checkable loginCheck(LoginInfo loginInfo) {
        final UserEntity user = userFactory.getByEmail(loginInfo.getEmail());
        return user.using(passwordEncoder).checkIfPasswordEqualsTo(loginInfo.getPassword());
    }
}
