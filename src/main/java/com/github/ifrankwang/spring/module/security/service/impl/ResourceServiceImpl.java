package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.api.converter.security.ResourceConverter;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;
import com.github.ifrankwang.spring.module.security.exception.ResourceNotFoundException;
import com.github.ifrankwang.spring.module.security.repo.ResourceRepo;
import com.github.ifrankwang.spring.module.security.service.ResourceService;
import com.github.ifrankwang.spring.util.UserInfoHolder;
import com.github.ifrankwang.utils.misc.Checkable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public ResourceEntity findById(Long id) throws ResourceNotFoundException {
        return repo.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<ResourceEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public List<ResourceEntity> getAllAsConstructed() {
        return repo.findAllByParentIsNull();
    }

    @Override
    public ResourceEntity create(ResourceEntity resource) throws ResourceExistedException {
        final UserEntity operator = UserInfoHolder.getUserInfo();
        resource.setCreateTime(LocalDateTime.now());
        resource.setCreator(operator);
        resource.completeRelation();
        Checkable.of(repo.existsByTag(resource.getTag())).ifTrueThrow(ResourceExistedException::new);
        return repo.save(resource);
    }

    @Override
    public ResourceEntity update(ResourceEntity resource) throws ResourceNotFoundException, ResourceExistedException {
        final ResourceEntity originalOne = repo.findById(resource.getId()).orElseThrow(ResourceNotFoundException::new);
        if (!StringUtils.equals(originalOne.getTag(), resource.getTag())) {
            Checkable.of(repo.existsByTag(resource.getTag())).ifTrueThrow(ResourceExistedException::new);
        }
        return ResourceConverter.INSTANCE.updateEntity(originalOne, resource);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, InsufficientPermissionException {
        final ResourceEntity resourceEntity = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (resourceEntity.getProtect()) {
            throw new InsufficientPermissionException();
        }
        repo.delete(resourceEntity);
    }
}
