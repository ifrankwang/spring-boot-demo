package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.UserConverter;
import com.github.ifrankwang.spring.api.dto.security.login.LoginInfo;
import com.github.ifrankwang.spring.api.dto.security.login.LoginResponse;
import com.github.ifrankwang.spring.api.facade.LoginFacade;
import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.*;
import com.github.ifrankwang.spring.module.security.service.RoleAuthorityService;
import com.github.ifrankwang.spring.module.security.service.RoleService;
import com.github.ifrankwang.spring.module.security.service.TokenService;
import com.github.ifrankwang.spring.module.security.service.UserService;
import com.github.ifrankwang.utils.list.ListUtils;
import com.github.ifrankwang.utils.misc.Checkable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class LoginFacadeImpl implements LoginFacade {
    private final UserService userService;
    private final RoleService roleService;
    private final RoleAuthorityService roleAuthorityService;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public LoginFacadeImpl(UserService userService, RoleService roleService, RoleAuthorityService roleAuthorityService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleAuthorityService = roleAuthorityService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @Override
    public LoginResponse login(LoginInfo loginInfo) throws UserNotFoundException, InvalidUsernameOrPasswordException, UserSuspendedException {
        final LoginResponse response = new LoginResponse();
        final UserEntity user = userService.findByEmail(loginInfo.getEmail());
        final List<RoleEntity> roles = roleService.findGenericRoleOfUser(user);
        final List<AuthorityEntity> authorities = roleAuthorityService.findByRoles(roles);

        user.using(passwordEncoder).checkIfPasswordEqualsTo(loginInfo.getPassword())
            .orElseThrow(InvalidUsernameOrPasswordException::new);
        Checkable.of(user.getEnabled()).orElseThrow(UserSuspendedException::new);

        response.setToken(tokenService.genTokenFor(loginInfo.getEmail()));
        response.setUserInfo(UserConverter.INSTANCE.toDto(user));
        response.setAuthorities(ListUtils.map(authorities, AuthorityEntity::getId));
        return response;
    }

    @Override
    public String login(String token) throws UserNotFoundException, LoginInfoExpiredException, InsufficientPermissionException {
        final String email = tokenService.getSubjectFrom(token);
        userService.findByEmail(email);
        return tokenService.genTokenFor(email);
    }
}
