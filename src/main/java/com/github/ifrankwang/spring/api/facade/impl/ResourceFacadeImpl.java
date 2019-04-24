package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.converter.security.ResourceConverter;
import com.github.ifrankwang.spring.api.dto.security.resource.BaseResourceDto;
import com.github.ifrankwang.spring.api.dto.security.resource.ConstructedResourceDto;
import com.github.ifrankwang.spring.api.dto.security.resource.ResourceDto;
import com.github.ifrankwang.spring.api.facade.ResourceFacade;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;
import com.github.ifrankwang.spring.module.security.exception.ResourceNotFoundException;
import com.github.ifrankwang.spring.module.security.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class ResourceFacadeImpl implements ResourceFacade {
    private final ResourceService resourceService;

    public ResourceFacadeImpl(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public List<ConstructedResourceDto> getConstructedResourceList() {
        final List<ResourceEntity> entities = resourceService.getAllAsConstructed();
        return ResourceConverter.INSTANCE.toConstructedDtoList(entities);
    }

    @Override
    public List<ResourceDto> getResourceList() {
        final List<ResourceEntity> entities = resourceService.findAll();
        return ResourceConverter.INSTANCE.toDtoList(entities);
    }

    @Override
    public ConstructedResourceDto createResource(BaseResourceDto request) throws OperationNotFoundException, ResourceExistedException {
        ResourceEntity entity = ResourceConverter.INSTANCE.toEntity(request);
        entity = resourceService.create(entity);
        return ResourceConverter.INSTANCE.toConstructedDto(entity);
    }

    @Override
    public ConstructedResourceDto updateResource(Long id, BaseResourceDto request) throws ResourceNotFoundException, OperationNotFoundException, ResourceExistedException {
        ResourceEntity entity = ResourceConverter.INSTANCE.toEntity(request, id);
        resourceService.update(entity);
        return ResourceConverter.INSTANCE.toConstructedDto(entity);
    }

    @Override
    public void deleteResource(Long id) throws ResourceNotFoundException {
        resourceService.delete(id);
    }
}
