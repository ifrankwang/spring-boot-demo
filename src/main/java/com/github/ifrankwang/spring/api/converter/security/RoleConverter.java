package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.dto.security.role.BaseRoleDto;
import com.github.ifrankwang.spring.api.dto.security.role.RoleDto;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.utils.page.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper
public interface RoleConverter {
    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    RoleDto toDto(RoleEntity entity);

    Page<RoleDto> toDto(Page<RoleEntity> entityPage);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    RoleEntity toEntity(BaseRoleDto baseRoleDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    RoleEntity update(@MappingTarget RoleEntity entity, BaseRoleDto baseRoleDto);
}
