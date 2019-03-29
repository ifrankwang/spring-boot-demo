package com.github.ifrankwang.spring.module.security.repo;

import com.github.ifrankwang.spring.interfaces.Repository;
import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleAuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface RoleAuthorityRepo extends Repository<RoleAuthorityEntity, Long> {

    /**
     * 获取角色相应权限列表
     *
     * @param roles     角色列表
     * @param authority 权限
     * @return 角色权限列表
     */
    List<RoleAuthorityEntity> findAllByRoleInAndAuthority(List<RoleEntity> roles, AuthorityEntity authority);
}
