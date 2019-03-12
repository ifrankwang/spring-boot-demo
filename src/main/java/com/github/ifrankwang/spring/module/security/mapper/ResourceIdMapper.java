package com.github.ifrankwang.spring.module.security.mapper;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.ResourceNotFoundException;
import com.github.ifrankwang.spring.module.security.repo.ResourceRepo;
import org.springframework.stereotype.Component;

/**
 * @author Frank Wang
 */
@Component
public class ResourceIdMapper {
    private final ResourceRepo repo;

    ResourceIdMapper(ResourceRepo repo) {
        this.repo = repo;
    }

    public ResourceEntity idToEntity(Long id) throws ResourceNotFoundException {
        return null == id ? null : repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public Long entityToId(ResourceEntity entity) {
        return null == entity ? null : entity.getId();
    }
}
