package com.github.ifrankwang.spring.module.system.service;

import com.github.ifrankwang.spring.exception.InternalServerError;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;

/**
 * @author Frank Wang
 */
public interface SystemConfigService {
    /**
     * 获取超级管理员
     *
     * @return 超级管理员
     */
    UserEntity getSuperAdmin() throws InternalServerError;
}
