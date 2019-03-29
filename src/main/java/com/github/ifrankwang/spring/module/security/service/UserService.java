package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;

import java.util.List;

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

    /**
     * 获取指定用户
     *
     * @param id 用户id
     * @return 指定用户
     * @throws UserNotFoundException 没有找到用户
     */
    UserEntity findById(Long id) throws UserNotFoundException;

    /**
     * 获取所有用户
     *
     * @return 所有用户
     */
    List<UserEntity> findAll();
}
