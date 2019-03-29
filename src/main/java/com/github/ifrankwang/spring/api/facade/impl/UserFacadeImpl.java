package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.UserConverter;
import com.github.ifrankwang.spring.api.dto.security.UserDto;
import com.github.ifrankwang.spring.api.facade.UserFacade;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDto> getUserList() {
        final List<UserEntity> entities = userService.findAll();
        return UserConverter.INSTANCE.toDtoList(entities);
    }
}
