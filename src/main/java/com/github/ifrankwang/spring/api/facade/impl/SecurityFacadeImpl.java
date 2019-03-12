package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.dto.security.*;
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

    private final SecurityDtoMapper securityDtoMapper;

    public SecurityFacadeImpl(ResourceService resourceService, OperationService operationService, SecurityDtoMapper securityDtoMapper) {
        this.resourceService = resourceService;
        this.operationService = operationService;
        this.securityDtoMapper = securityDtoMapper;
    }

    @Override
    public List<OperationDto> getOperationList() {
        final List<OperationEntity> operationEntities = operationService.getAll();
        return securityDtoMapper.fromOperationEntities(operationEntities);
    }

    @Override
    public OperationDto createOperation(OperationRequest request) throws OperationExistedException {
        OperationEntity operationEntity = securityDtoMapper.toOperationEntity(request);
        operationEntity = operationService.create(operationEntity);
        return securityDtoMapper.fromOperationEntity(operationEntity);
    }

    @Override
    public OperationDto updateOperation(Long id, OperationRequest request) throws OperationExistedException {
        OperationEntity updateEntity = securityDtoMapper.toOperationEntity(request, id);
        updateEntity = operationService.update(updateEntity);
        return securityDtoMapper.fromOperationEntity(updateEntity);
    }

    @Override
    public void deleteOperation(Long id) throws OperationNotFoundException, OperationOccupiedException {
        operationService.delete(id);
    }

    @Override
    public List<ResourceDto> getResourceList() {
        final List<ResourceEntity> resourceEntities = resourceService.getAllAsContracted();
        return securityDtoMapper.fromResourceEntities(resourceEntities);
    }

    @Override
    public ResourceDto createResource(SingleResourceRequest request) throws OperationNotFoundException, ResourceExistedException {
        ResourceEntity resourceEntity = securityDtoMapper.toResourceEntity(request);
        resourceEntity = resourceService.create(resourceEntity);
        return securityDtoMapper.fromResourceEntity(resourceEntity);
    }

    @Override
    public ResourceDto updateResource(Long id, SingleResourceRequest request) throws ResourceNotFoundException, OperationNotFoundException, ResourceExistedException {
        ResourceEntity resourceEntity = securityDtoMapper.toResourceEntity(request, id);
        resourceService.update(resourceEntity);
        return securityDtoMapper.fromResourceEntity(resourceEntity);
    }

    @Override
    public void deleteResource(Long id) throws ResourceNotFoundException {
        resourceService.delete(id);
    }
}
