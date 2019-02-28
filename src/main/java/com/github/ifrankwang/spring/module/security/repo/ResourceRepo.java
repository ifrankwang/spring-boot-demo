package com.github.ifrankwang.spring.module.security.repo;

import com.github.ifrankwang.spring.interfaces.Repository;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;

import java.util.List;

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

    /**
     * 判断是否存在占用操作的资源
     *
     * @param operation 操作
     * @return 是否存在
     */
    boolean existsByOperationsContains(OperationEntity operation);

    /**
     * 获取结构化的模块列表
     *
     * @return 结构化的模块列表
     */
    List<ResourceEntity> findAllByParentIsNull();
}
