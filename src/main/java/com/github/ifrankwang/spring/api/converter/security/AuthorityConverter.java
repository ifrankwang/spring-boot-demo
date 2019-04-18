package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.dto.security.authority.BaseAuthorityDto;
import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper(uses = {ApiIdConverter.class})
public interface AuthorityConverter {
    AuthorityConverter INSTANCE = Mappers.getMapper(AuthorityConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resource", ignore = true)
    @Mapping(target = "api", source = "apiId")
    AuthorityEntity toEntity(BaseAuthorityDto baseDto);
}
