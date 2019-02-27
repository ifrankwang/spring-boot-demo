package com.github.ifrankwang.spring.api.dto.mapper;

import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @author Frank Wang
 */
@Mapper(componentModel = "spring", uses = {ResourceIdMapper.class, OperationIdMapper.class})
public interface ResourceMapper {

    @Mapping(target = "parent", source = "parentId")
    ResourceEntity toResourceEntity(SingleResourceRequest request);

    @Mapping(target = "parentId", source = "parent")
    ResourceDto fromResourceEntity(ResourceEntity resource);

    @Mapping(target = "id", ignore = true)
    OperationEntity toOperationEntity(OperationRequest dto);

    OperationDto fromOperationEntity(OperationEntity entity);
}
