package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;
import com.github.ifrankwang.spring.module.security.query.ApiQuery;
import com.github.ifrankwang.spring.module.security.repo.ApiRepo;
import com.github.ifrankwang.spring.module.security.service.ApiService;
import com.github.ifrankwang.spring.util.UserInfoHolder;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Frank Wang
 */
@Service
public class ApiServiceImpl implements ApiService {
    private final ApiRepo repo;
    private final ApiQuery query;

    public ApiServiceImpl(ApiRepo repo, ApiQuery query) {
        this.repo = repo;
        this.query = query;
    }

    @Override
    public ApiEntity create(ApiEntity entity) {
        final UserEntity operator = UserInfoHolder.getUserInfo();
        entity.setCreator(operator);
        entity.setCreateTime(LocalDateTime.now());
        return repo.save(entity);
    }

    @Override
    public Page<ApiEntity> findAll(Pageable pageable) {
        return query.findAll(pageable);
    }

    @Override
    public ApiEntity findById(Long id) throws ApiNotFoundException {
        return repo.findById(id).orElseThrow(ApiNotFoundException::new);
    }

    @Override
    public ApiEntity findByMethodAndPath(ApiMethod method, String path) throws ApiNotFoundException {
        return repo.findFirstByMethodAndPath(method, path).orElseThrow(ApiNotFoundException::new);
    }

    @Override
    public Optional<ApiEntity> findOptionalByMethodAndPath(ApiMethod method, String path) {
        return repo.findFirstByMethodAndPath(method, path);
    }

    @Override
    public void delete(ApiEntity entity) {
        repo.delete(entity);
    }
}
