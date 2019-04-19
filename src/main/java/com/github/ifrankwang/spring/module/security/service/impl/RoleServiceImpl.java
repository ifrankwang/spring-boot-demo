package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.RoleNotFoundException;
import com.github.ifrankwang.spring.module.security.query.RoleQuery;
import com.github.ifrankwang.spring.module.security.repo.RoleRepo;
import com.github.ifrankwang.spring.module.security.service.RoleService;
import com.github.ifrankwang.spring.util.UserInfoHolder;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo repo;
    private final RoleQuery query;

    public RoleServiceImpl(RoleRepo repo, RoleQuery query) {
        this.repo = repo;
        this.query = query;
    }

    @Override
    public RoleEntity findById(Long id) throws RoleNotFoundException {
        return repo.findById(id).orElseThrow(RoleNotFoundException::new);
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

    @Override
    public RoleEntity create(RoleEntity entity) {
        entity.setCreator(UserInfoHolder.getUserInfo());
        entity.setCreateTime(LocalDateTime.now());
        return repo.save(entity);
    }
}
