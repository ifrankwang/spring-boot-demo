package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.UserConverter;
import com.github.ifrankwang.spring.api.dto.security.UserDto;
import com.github.ifrankwang.spring.api.facade.UserFacade;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
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
}
