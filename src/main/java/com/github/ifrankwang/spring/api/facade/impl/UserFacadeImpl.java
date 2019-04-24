package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.UserConverter;
import com.github.ifrankwang.spring.api.dto.security.user.BaseUserDto;
import com.github.ifrankwang.spring.api.dto.security.user.UserDto;
import com.github.ifrankwang.spring.api.facade.UserFacade;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.service.UserService;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.stereotype.Service;

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
    public Page<UserDto> getUserList(Pageable pageable) {
        final Page<UserEntity> entities = userService.findAll(pageable);
        return UserConverter.INSTANCE.toDto(entities);
    }

    @Override
    public UserDto createUser(BaseUserDto baseUserDto) {
        UserEntity entity = UserConverter.INSTANCE.toEntity(baseUserDto);
        entity = userService.create(entity);
        return UserConverter.INSTANCE.toDto(entity);
    }

    @Override
    public void suspendOrActiveUser(Long userId) throws UserNotFoundException {
        final UserEntity entity = userService.findById(userId);
        entity.setEnabled(!entity.getEnabled());
    }

    @Override
    public UserDto updateUser(Long userId, BaseUserDto baseUserDto) throws UserNotFoundException {
        UserEntity entity = userService.findById(userId);
        entity = UserConverter.INSTANCE.update(entity, baseUserDto);
        entity = userService.update(entity);
        return UserConverter.INSTANCE.toDto(entity);
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        final UserEntity entity = userService.findById(userId);
        entity.setEnabled(false);
    }
}
