package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.resource.BaseResourceDto;
import com.github.ifrankwang.spring.api.dto.security.resource.ResourceDto;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;
import com.github.ifrankwang.spring.module.security.exception.ResourceNotFoundException;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface ResourceFacade {

    /**
     * 获取结构化的模块列表
     *
     * @return 结构化的模块列表
     */
    List<ResourceDto> getResourceList();

    /**
     * 创建一个新的资源/模块
     *
     * @param request 请求体
     * @return 详细信息的资源/模块对象
     * @throws OperationNotFoundException 模块不存在
     * @throws ResourceExistedException 同标签名模块已存在
     */
    ResourceDto createResource(BaseResourceDto request) throws OperationNotFoundException, ResourceExistedException;

    /**
     * 更新模块信息
     *
     * @param id      模块id
     * @param request 请求体
     * @return 更新后的模块信息
     * @throws ResourceNotFoundException  模块不存在
     * @throws OperationNotFoundException 操作不存在
     * @throws ResourceExistedException   同标签名模块已存在
     */
    ResourceDto updateResource(Long id, BaseResourceDto request) throws ResourceNotFoundException, OperationNotFoundException, ResourceExistedException;

    /**
     * 删除模块
     *
     * @param id 模块id
     * @throws ResourceNotFoundException 模块不存在
     */
    void deleteResource(Long id) throws ResourceNotFoundException;
}
