package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface RoleService {
    /**
     * 获取用户非业务类角色列表
     *
     * @param user 用户
     * @return 非业务类角色列表
     */
    List<RoleEntity> findGenericRoleOfUser(UserEntity user);

    /**
     * 获取用户业务类角色列表
     *
     * @param user  用户
     * @param group 组
     * @return 业务类角色
     */
    List<RoleEntity> findBusinessRoleOfUser(UserEntity user, GroupEntity group);
}
