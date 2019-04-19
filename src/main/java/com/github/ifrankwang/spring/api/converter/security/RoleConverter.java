package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.dto.security.RoleDto;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.utils.page.Page;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper
public interface RoleConverter {
    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    RoleDto toDto(RoleEntity entity);

    Page<RoleDto> toDto(Page<RoleEntity> entityPage);
}
