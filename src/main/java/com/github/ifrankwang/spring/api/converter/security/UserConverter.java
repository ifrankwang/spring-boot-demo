package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.dto.security.UserDto;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.utils.page.Page;
import org.mapstruct.Mapper;
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
}
