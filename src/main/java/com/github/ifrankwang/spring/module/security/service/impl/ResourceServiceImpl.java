package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;
import com.github.ifrankwang.spring.module.security.repo.ResourceRepo;
import com.github.ifrankwang.spring.module.security.service.ResourceService;
import com.github.ifrankwang.utils.misc.Checkable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepo repo;

    public ResourceServiceImpl(ResourceRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<ResourceEntity> getAllAsContracted() {
        return repo.findAllByParentIsNull();
    }

    @Override
    public ResourceEntity create(ResourceEntity resource) throws ResourceExistedException {
        Checkable.of(repo.existsByTag(resource.getTag())).ifTrueThrow(ResourceExistedException::new);
        return repo.save(resource);
    }
}
