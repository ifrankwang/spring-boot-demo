package com.github.ifrankwang.spring.api.dto.mapper;

import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author Frank Wang
 */
@Mapper(componentModel = "spring", uses = {ResourceIdMapper.class, OperationIdMapper.class})
public interface ResourceMapper {

    @Mapping(target = "parent", source = "parentId")
    ResourceEntity toResourceEntity(SingleResourceRequest request) throws OperationNotFoundException;

    @Mapping(target = "parent", source = "request.parentId")
    ResourceEntity toResourceEntity(SingleResourceRequest request, Long id) throws OperationNotFoundException;

    @Mapping(target = "parentId", source = "parent")
    ResourceDto fromResourceEntity(ResourceEntity resource);

    List<ResourceDto> fromResourceEntities(List<ResourceEntity> resources);

    @Mapping(target = "id", ignore = true)
    OperationEntity toOperationEntity(OperationRequest dto);

    OperationEntity toOperationEntity(OperationRequest dto, Long id);

    OperationDto fromOperationEntity(OperationEntity entity);

    List<OperationDto> fromOperationEntities(List<OperationEntity> entity);
}
