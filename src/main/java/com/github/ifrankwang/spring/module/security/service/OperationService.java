package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationExistedException;

/**
 * @author Frank Wang
 */
public interface OperationService {

    /**
     * 创建模块可执行的操作
     *
     * @param entity 操作实体类对象
     * @return 保存后的操作实体类对象
     * @throws OperationExistedException 同标签名操作已存在
     */
    OperationEntity create(OperationEntity entity) throws OperationExistedException;
}
