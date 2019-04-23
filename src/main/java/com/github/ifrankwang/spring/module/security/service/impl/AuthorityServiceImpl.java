package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.repo.AuthorityRepo;
import com.github.ifrankwang.spring.module.security.service.AuthorityService;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepo repo;

    public AuthorityServiceImpl(AuthorityRepo repo) {
        this.repo = repo;
    }

    @Override
    public AuthorityEntity findByApi(ApiEntity apiEntity) {
        return repo.findFirstByApi(apiEntity);
    }
}
