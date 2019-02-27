package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;

/**
 * @author Frank Wang
 */
public interface ResourceService {

    /**
     * 新建模块，不允许标签重名
     *
     * @param resource 模块对象
     * @return 模块对象，包含id
     * @throws ResourceExistedException 同标签名模块已存在
     */
    ResourceEntity create(ResourceEntity resource) throws ResourceExistedException;
}
