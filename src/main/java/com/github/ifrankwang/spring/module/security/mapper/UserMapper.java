package com.github.ifrankwang.spring.module.security.mapper;

import com.github.ifrankwang.spring.api.dto.security.UserDto;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

/**
 * @author Frank Wang
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto fromEntity(UserEntity user);

    List<UserDto> fromEntity(List<UserEntity> users);

    default List<String> mapRoles(List<RoleEntity> roles) {
        if (null == roles) {
            return emptyList();
        }

        return roles.stream().map(RoleEntity::getName).collect(Collectors.toList());
    }
}
