package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.dto.security.ApiDto;
import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.utils.page.Page;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper
public interface ApiConverter {
    ApiConverter INSTANCE = Mappers.getMapper(ApiConverter.class);

    ApiDto toDto(ApiEntity entity);

    @InheritInverseConfiguration
    ApiEntity toEntity(ApiDto dto);

    Page<ApiDto> toPage(Page<ApiEntity> entityPage);
}
