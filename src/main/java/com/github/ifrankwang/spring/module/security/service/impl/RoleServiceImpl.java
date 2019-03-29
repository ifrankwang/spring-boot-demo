package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.query.RoleQuery;
import com.github.ifrankwang.spring.module.security.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleQuery query;

    public RoleServiceImpl(RoleQuery query) {
        this.query = query;
    }

    @Override
    public List<RoleEntity> findGenericRoleOfUser(UserEntity user) {
        return query.findGenericRoleOfUser(user);
    }

    @Override
    public List<RoleEntity> findBusinessRoleOfUser(UserEntity user, GroupEntity group) {
        return query.findBusinessRoleOfUser(user, group);
    }
}
