package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.query.RoleQuery;
import com.github.ifrankwang.spring.module.security.service.RoleService;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
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
    public Page<RoleEntity> findAll(Pageable pageable) {
        return query.findAll(pageable);
    }

    @Override
    public Page<RoleEntity> findByGeneric(Pageable pageable, Boolean generic) {
        return query.findByGeneric(pageable, generic);
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
