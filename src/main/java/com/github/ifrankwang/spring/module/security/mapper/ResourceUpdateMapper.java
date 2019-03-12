package com.github.ifrankwang.spring.module.security.mapper;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper
public interface ResourceUpdateMapper {
    ResourceUpdateMapper INSTANCE = Mappers.getMapper(ResourceUpdateMapper.class);

    @Mapping(target = "children", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    ResourceEntity update(@MappingTarget ResourceEntity dest, ResourceEntity source);
}
