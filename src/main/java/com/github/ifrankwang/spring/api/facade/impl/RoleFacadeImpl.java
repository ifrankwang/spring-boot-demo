package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.RoleConverter;
import com.github.ifrankwang.spring.api.dto.security.RoleDto;
import com.github.ifrankwang.spring.api.facade.RoleFacade;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.service.RoleService;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.stereotype.Service;

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
}
