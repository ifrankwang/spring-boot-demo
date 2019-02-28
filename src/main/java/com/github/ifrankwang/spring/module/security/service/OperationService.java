package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationExistedException;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import com.github.ifrankwang.spring.module.security.exception.OperationOccupiedException;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface OperationService {
    /**
     * 获取模块可执行的所有操作
     *
     * @return 可执行的操作实体类对象列表
     */
    List<OperationEntity> getAll();

    /**
     * 创建模块可执行的操作
     *
     * @param entity 操作实体类对象
     * @return 保存后的操作实体类对象
     * @throws OperationExistedException 同标签名操作已存在
     */
    OperationEntity create(OperationEntity entity) throws OperationExistedException;

    /**
     * 更新模块可执行的操作
     *
     * @param entity 操作实体类对象
     * @return 更新后的操作实体类对象
     * @throws OperationNotFoundException 操作不存在
     * @throws OperationExistedException  同标签名操作已存在
     */
    OperationEntity update(OperationEntity entity) throws OperationNotFoundException, OperationExistedException;

    /**
     * 删除模块可执行的操作
     *
     * @param id 模块id
     * @throws OperationNotFoundException 操作不存在
     * @throws OperationOccupiedException 操作被占用
     */
    void delete(Long id) throws OperationNotFoundException, OperationOccupiedException;
}
