package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.UserDto;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface UserFacade {

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    List<UserDto> getUserList();
}
