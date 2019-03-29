package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;
import com.github.ifrankwang.spring.module.security.repo.ApiRepo;
import com.github.ifrankwang.spring.module.security.service.ApiService;
import org.springframework.stereotype.Service;

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
    public ApiEntity findByPath(String path) throws ApiNotFoundException {
        return repo.findFirstByPath(path).orElseThrow(ApiNotFoundException::new);
    }
}
