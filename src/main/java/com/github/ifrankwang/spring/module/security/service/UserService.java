package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;

/**
 * @author Frank Wang
 */
public interface UserService {
    /**
     * 根据邮箱获取用户实体对象
     *
     * @param email 邮箱
     * @return 用户实体对象
     * @throws UserNotFoundException 没有找到用户
     */
    UserEntity findByEmail(String email) throws UserNotFoundException;
}
