package com.github.ifrankwang.spring.api.dto.security;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper(uses = ParentMapper.class)
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    @Mapping(target = "parent", source = "parentId")
    ResourceEntity fromEntity(SingleResourceRequest request);

    @Mapping(source = "parent.id", target = "parentId")
    ResourceDto fromEntity(ResourceEntity resource);
}

class ParentMapper {
    ResourceEntity idToEntity(Long id) {
        return null == id ? null : new ResourceEntity(id);
    }
}