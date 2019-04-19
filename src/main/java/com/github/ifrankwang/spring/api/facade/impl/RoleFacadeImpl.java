package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.AuthorityConverter;
import com.github.ifrankwang.spring.api.converter.security.RoleConverter;
import com.github.ifrankwang.spring.api.dto.security.role.BaseRoleDto;
import com.github.ifrankwang.spring.api.dto.security.role.RoleDto;
import com.github.ifrankwang.spring.api.facade.RoleFacade;
import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.exception.RoleNotFoundException;
import com.github.ifrankwang.spring.module.security.service.RoleService;
import com.github.ifrankwang.utils.list.ListUtils;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class RoleFacadeImpl implements RoleFacade {
    private final RoleService roleService;

    public RoleFacadeImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Page<RoleDto> findAll(Pageable pageable) {
        final Page<RoleEntity> roleEntityPage = roleService.findAll(pageable);
        return RoleConverter.INSTANCE.toDto(roleEntityPage);
    }

    @Override
    public Page<RoleDto> findByGeneric(Pageable pageable, Boolean generic) {
        final Page<RoleEntity> roleEntityPage = roleService.findByGeneric(pageable, generic);
        return RoleConverter.INSTANCE.toDto(roleEntityPage);
    }

    @Override
    public List<Long> getRoleAuthorityIdList(Long roleId) throws RoleNotFoundException {
        final RoleEntity roleEntity = roleService.findById(roleId);
        return ListUtils.map(roleEntity.getAuthorities(), AuthorityEntity::getId);
    }

    @Override
    public void updateRoleAuthorityIdList(Long roleId, List<Long> authorityIdList) throws RoleNotFoundException {
        final RoleEntity roleEntity = roleService.findById(roleId);
        final List<AuthorityEntity> authorityEntities = AuthorityConverter.INSTANCE.toEntities(authorityIdList);
        roleEntity.setAuthorities(authorityEntities);
    }

    @Override
    public RoleDto createRole(BaseRoleDto baseRoleDto) {
        RoleEntity entity = RoleConverter.INSTANCE.toEntity(baseRoleDto);
        entity = roleService.create(entity);
        return RoleConverter.INSTANCE.toDto(entity);
    }
}
