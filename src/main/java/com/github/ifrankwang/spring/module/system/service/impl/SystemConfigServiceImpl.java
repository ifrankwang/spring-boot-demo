package com.github.ifrankwang.spring.module.system.service.impl;

import com.github.ifrankwang.spring.exception.InternalServerError;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.system.entity.SystemConfigEntity;
import com.github.ifrankwang.spring.module.system.repo.SystemConfigRepo;
import com.github.ifrankwang.spring.module.system.service.SystemConfigService;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {
    private final SystemConfigRepo repo;

    public SystemConfigServiceImpl(SystemConfigRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserEntity getSuperAdmin() throws InternalServerError {
        final SystemConfigEntity entity = repo.findById(1L).orElseThrow(InternalServerError::new);
        return entity.getSystemAdmin();
    }
}
