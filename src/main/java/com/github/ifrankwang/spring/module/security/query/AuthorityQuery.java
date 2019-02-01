package com.github.ifrankwang.spring.module.security.query;

import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface AuthorityQuery {
    /**
     * 获取用户权限列表
     *
     * @param user 用户
     * @return 权限列表
     */
    List<AuthorityEntity> findAllByUser(UserEntity user);
}
