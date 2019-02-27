package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationExistedException;
import com.github.ifrankwang.spring.module.security.repo.OperationRepo;
import com.github.ifrankwang.spring.module.security.service.OperationService;
import com.github.ifrankwang.utils.misc.Checkable;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepo repo;

    public OperationServiceImpl(OperationRepo repo) {
        this.repo = repo;
    }

    @Override
    public OperationEntity create(OperationEntity entity) throws OperationExistedException {
        Checkable.of(repo.existsByTag(entity.getTag())).ifTrueThrow(OperationExistedException::new);
        return repo.save(entity);
    }
}
