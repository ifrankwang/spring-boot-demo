package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleAuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface RoleAuthorityService {
    /**
     * 获取角色相应权限列表
     *
     * @param roles     角色列表
     * @param authority 权限
     * @return 角色权限列表
     */
    List<RoleAuthorityEntity> findByRolesAndAuthority(List<RoleEntity> roles, AuthorityEntity authority);

    /**
     * 获取角色的权限列表
     *
     * @param roles 角色实体对象列表
     * @return 角色的权限列表
     */
    List<AuthorityEntity> findByRoles(List<RoleEntity> roles);
}
