package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationExistedException;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import com.github.ifrankwang.spring.module.security.mapper.OperationMapper;
import com.github.ifrankwang.spring.module.security.repo.OperationRepo;
import com.github.ifrankwang.spring.module.security.service.OperationService;
import com.github.ifrankwang.utils.misc.Checkable;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<OperationEntity> getAll() {
        return repo.findAll();
    }

    @Override
    public OperationEntity create(OperationEntity entity) throws OperationExistedException {
        Checkable.of(repo.existsByTag(entity.getTag())).ifTrueThrow(OperationExistedException::new);
        return repo.save(entity);
    }

    @Override
    public OperationEntity update(OperationEntity entity) throws OperationNotFoundException, OperationExistedException {
        final OperationEntity originalOne = repo.findById(entity.getId()).orElseThrow(OperationNotFoundException::new);
        if (!StringUtils.equals(originalOne.getTag(), entity.getTag())) {
            Checkable.of(repo.existsByTag(entity.getTag())).ifTrueThrow(OperationExistedException::new);
        }
        OperationMapper.INSTANCE.update(originalOne, entity);
        return originalOne;
    }
}
