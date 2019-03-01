package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.dto.mapper.ResourceMapper;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.api.facade.SecurityFacade;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.*;
import com.github.ifrankwang.spring.module.security.service.OperationService;
import com.github.ifrankwang.spring.module.security.service.ResourceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class SecurityFacadeImpl implements SecurityFacade {
    private final ResourceService resourceService;
    private final OperationService operationService;

    private final ResourceMapper resourceMapper;

    public SecurityFacadeImpl(ResourceService resourceService, OperationService operationService, ResourceMapper resourceMapper) {
        this.resourceService = resourceService;
        this.operationService = operationService;
        this.resourceMapper = resourceMapper;
    }

    @Override
    public List<OperationDto> getOperationList() {
        final List<OperationEntity> operationEntities = operationService.getAll();
        return resourceMapper.fromOperationEntities(operationEntities);
    }

    @Override
    public OperationDto createOperation(OperationRequest request) throws OperationExistedException {
        OperationEntity operationEntity = resourceMapper.toOperationEntity(request);
        operationEntity = operationService.create(operationEntity);
        return resourceMapper.fromOperationEntity(operationEntity);
    }

    @Override
    public OperationDto updateOperation(Long id, OperationRequest request) throws OperationExistedException {
        OperationEntity updateEntity = resourceMapper.toOperationEntity(request, id);
        updateEntity = operationService.update(updateEntity);
        return resourceMapper.fromOperationEntity(updateEntity);
    }

    @Override
    public void deleteOperation(Long id) throws OperationNotFoundException, OperationOccupiedException {
        operationService.delete(id);
    }

    @Override
    public List<ResourceDto> getResourceList() {
        final List<ResourceEntity> resourceEntities = resourceService.getAllAsContracted();
        return resourceMapper.fromResourceEntities(resourceEntities);
    }

    @Override
    public ResourceDto createResource(SingleResourceRequest request) throws OperationNotFoundException, ResourceExistedException {
        ResourceEntity resourceEntity = resourceMapper.toResourceEntity(request);
        resourceEntity = resourceService.create(resourceEntity);
        return resourceMapper.fromResourceEntity(resourceEntity);
    }

    @Override
    public ResourceDto updateResource(Long id, SingleResourceRequest request) throws ResourceNotFoundException, OperationNotFoundException, ResourceExistedException {
        ResourceEntity resourceEntity = resourceMapper.toResourceEntity(request, id);
        resourceService.update(resourceEntity);
        return resourceMapper.fromResourceEntity(resourceEntity);
    }
}
