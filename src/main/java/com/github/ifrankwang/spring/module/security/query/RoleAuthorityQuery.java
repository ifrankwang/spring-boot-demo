package com.github.ifrankwang.spring.module.security.query;

import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface RoleAuthorityQuery {

    /**
     * 获取角色的权限列表
     *
     * @param roles 角色实体对象列表
     * @return 角色的权限列表
     */
    List<AuthorityEntity> findByRoles(List<RoleEntity> roles);
}
