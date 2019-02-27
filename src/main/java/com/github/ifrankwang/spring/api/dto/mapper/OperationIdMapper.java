package com.github.ifrankwang.spring.api.dto.mapper;

import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import com.github.ifrankwang.spring.module.security.repo.OperationRepo;
import org.springframework.stereotype.Component;

/**
 * @author Frank Wang
 */
@Component
public class OperationIdMapper {
    private final OperationRepo repo;

    public OperationIdMapper(OperationRepo repo) {
        this.repo = repo;
    }

    public OperationEntity idToEntity(Long id) throws OperationNotFoundException {
        return null == id ? null : repo.findById(id).orElse(null);
    }

    public Long entityToId(OperationEntity entity) {
        return null == entity ? null : entity.getId();
    }
}