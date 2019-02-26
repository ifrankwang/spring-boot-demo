package com.github.ifrankwang.spring.module.security.repo;

import com.github.ifrankwang.spring.interfaces.Repository;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;

/**
 * @author Frank Wang
 */
public interface ResourceRepo extends Repository<ResourceEntity, Long> {
    /**
     * 判断是否存在同标签名资源
     *
     * @param tag 标签名
     * @return 是否存在
     */
    boolean existsByTag(String tag);
}
