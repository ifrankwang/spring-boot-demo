package com.github.ifrankwang.spring.module.security.repo;

import com.github.ifrankwang.spring.interfaces.Repository;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;

/**
 * @author Frank Wang
 */
public interface OperationRepo extends Repository<OperationEntity, Long> {
    /**
     * 判断相应标签名的操作是否已存在
     *
     * @param tag 标签名
     * @return 是否存在
     */
    boolean existsByTag(String tag);
}
