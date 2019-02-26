package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.dto.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.security.ResourceMapper;
import com.github.ifrankwang.spring.api.dto.security.SingleResourceRequest;
import com.github.ifrankwang.spring.api.facade.SecurityFacade;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.service.ResourceService;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class SecurityFacadeImpl implements SecurityFacade {
    private final ResourceService resourceService;

    public SecurityFacadeImpl(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public ResourceDto createResource(SingleResourceRequest request) {
        ResourceEntity resourceEntity = ResourceMapper.INSTANCE.fromEntity(request);
        resourceEntity = resourceService.create(resourceEntity);
        return ResourceMapper.INSTANCE.fromEntity(resourceEntity);
    }
}
