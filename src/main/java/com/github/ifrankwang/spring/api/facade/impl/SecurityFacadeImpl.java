package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.dto.mapper.ResourceMapper;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.api.facade.SecurityFacade;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationExistedException;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;
import com.github.ifrankwang.spring.module.security.service.OperationService;
import com.github.ifrankwang.spring.module.security.service.ResourceService;
import org.springframework.stereotype.Service;

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
    public OperationDto createOperation(OperationRequest request) throws OperationExistedException {
        OperationEntity operationEntity = resourceMapper.toOperationEntity(request);
        operationEntity = operationService.create(operationEntity);
        return resourceMapper.fromOperationEntity(operationEntity);
    }

    @Override
    public ResourceDto createResource(SingleResourceRequest request) throws ResourceExistedException {
        ResourceEntity resourceEntity = resourceMapper.toResourceEntity(request);
        resourceEntity = resourceService.create(resourceEntity);
        return resourceMapper.fromResourceEntity(resourceEntity);
    }
}
