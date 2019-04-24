package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;
import com.github.ifrankwang.spring.module.security.exception.ResourceNotFoundException;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface ResourceService extends BusinessGetter {

    /**
     * 获取指定模块
     *
     * @param id 模块id
     * @return 指定模块
     * @throws ResourceNotFoundException 模块未找到
     */
    @Override
    ResourceEntity findById(Long id) throws ResourceNotFoundException;

    /**
     * 获取模块列表
     *
     * @return 模块列表
     */
    List<ResourceEntity> findAll();

    /**
     * 获取结构化的模块列表
     *
     * @return 结构化的模块列表
     */
    List<ResourceEntity> getAllAsConstructed();

    /**
     * 新建模块，不允许标签重名
     *
     * @param resource 模块对象
     * @return 模块对象，包含id
     * @throws ResourceExistedException 同标签名模块已存在
     */
    ResourceEntity create(ResourceEntity resource) throws ResourceExistedException;

    /**
     * 更新模块
     *
     * @param resource 模块对象
     * @return 更新后的模块对象
     * @throws ResourceNotFoundException 模块不存在
     * @throws ResourceExistedException 同标签名模块已存在
     */
    ResourceEntity update(ResourceEntity resource) throws ResourceNotFoundException, ResourceExistedException;

    /**
     * 删除模块
     *
     * @param id 模块id
     * @throws ResourceNotFoundException 模块不存在
     * @throws InsufficientPermissionException 权限不足
     */
    void delete(Long id) throws ResourceNotFoundException, InsufficientPermissionException;
}
