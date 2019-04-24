package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.dto.security.user.BaseUserDto;
import com.github.ifrankwang.spring.api.dto.security.user.UserDto;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.utils.page.Page;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Frank Wang
 */
@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDto toDto(UserEntity user);

    List<UserDto> toDtoList(List<UserEntity> users);

    Page<UserDto> toDto(Page<UserEntity> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    UserEntity toEntity(BaseUserDto baseUserDto);

    @InheritConfiguration
    UserEntity update(@MappingTarget UserEntity entity, BaseUserDto baseUserDto);
}
