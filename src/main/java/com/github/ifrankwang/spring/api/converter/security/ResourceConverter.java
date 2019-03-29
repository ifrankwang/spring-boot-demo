package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.api.converter.ListStringConverter;
import com.github.ifrankwang.spring.api.dto.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.security.SingleResourceRequest;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Frank Wang
 */
@Mapper(uses = {ResourceIdConverter.class, ListStringConverter.class})
public interface ResourceConverter {
    ResourceConverter INSTANCE = Mappers.getMapper(ResourceConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", source = "parentId")
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "children", ignore = true)
    ResourceEntity toEntity(SingleResourceRequest request);

    @Mapping(target = "parent", source = "request.parentId")
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "children", ignore = true)
    ResourceEntity toEntity(SingleResourceRequest request, Long id);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    ResourceEntity updateEntity(@MappingTarget ResourceEntity dest, ResourceEntity source);

    @Mapping(target = "parentId", source = "parent.id")
    ResourceDto toDto(ResourceEntity resource);

    List<ResourceDto> toDtoList(List<ResourceEntity> resources);
}
