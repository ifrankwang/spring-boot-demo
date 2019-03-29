package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleAuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.repo.RoleAuthorityRepo;
import com.github.ifrankwang.spring.module.security.service.RoleAuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {
    private final RoleAuthorityRepo repo;

    public RoleAuthorityServiceImpl(RoleAuthorityRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<RoleAuthorityEntity> findByRolesAndAuthority(List<RoleEntity> roles, AuthorityEntity authority) {
        return repo.findAllByRoleInAndAuthority(roles, authority);
    }
}
