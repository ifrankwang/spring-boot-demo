package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.dto.security.UserDto;
import com.github.ifrankwang.spring.api.facade.UserFacade;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.mapper.UserMapper;
import com.github.ifrankwang.spring.module.security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserFacadeImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getUserList() {
        final List<UserEntity> users = userService.findAll();
        return userMapper.fromEntity(users);
    }
}
