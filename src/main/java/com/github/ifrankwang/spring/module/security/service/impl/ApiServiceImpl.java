package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;
import com.github.ifrankwang.spring.module.security.repo.ApiRepo;
import com.github.ifrankwang.spring.module.security.service.ApiService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Frank Wang
 */
@Service
public class ApiServiceImpl implements ApiService {
    private final ApiRepo repo;

    public ApiServiceImpl(ApiRepo repo) {
        this.repo = repo;
    }

    @Override
    public ApiEntity create(ApiEntity entity) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserEntity operator = (UserEntity) authentication.getDetails();
        entity.setCreator(operator);
        entity.setCreateTime(LocalDateTime.now());
        return repo.save(entity);
    }

    @Override
    public ApiEntity findByPath(String path) throws ApiNotFoundException {
        return repo.findFirstByPath(path).orElseThrow(ApiNotFoundException::new);
    }

    @Override
    public ApiEntity findByMethodAndPath(ApiMethod method, String path) throws ApiNotFoundException {
        return repo.findFirstByMethodAndPath(method, path).orElseThrow(ApiNotFoundException::new);
    }

    @Override
    public Optional<ApiEntity> findOptionalByMethodAndPath(ApiMethod method, String path) {
        return repo.findFirstByMethodAndPath(method, path);
    }
}
