package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

/**
 * @author Frank Wang
 */
public interface UserService extends BusinessGetter {
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
    @Override
    UserEntity findById(Long id) throws UserNotFoundException;

    /**
     * 获取所有用户
     *
     * @param pageable 分页数据
     * @return 所有用户
     */
    Page<UserEntity> findAll(Pageable pageable);

    /**
     * 创建用户
     *
     * @param entity 未保存的用户实体对象
     * @return 已保存的用户实体对象
     */
    UserEntity create(UserEntity entity);
}
