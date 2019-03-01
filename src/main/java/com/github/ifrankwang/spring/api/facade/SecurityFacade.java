package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.module.security.exception.*;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface SecurityFacade {

    /**
     * 获取模块可执行的操作
     *
     * @return 操作列表
     */
    List<OperationDto> getOperationList();

    /**
     * 创建模块可执行的操作
     *
     * @param request 请求体
     * @return 详细信息的操作对象
     * @throws OperationExistedException 同标签名操作已存在
     */
    OperationDto createOperation(OperationRequest request) throws OperationExistedException;

    /**
     * 更新模块可执行的操作
     *
     * @param id      模块id
     * @param request 请求体
     * @return 更新后的操作对象
     * @throws OperationExistedException 同标签名操作已存在
     */
    OperationDto updateOperation(Long id, OperationRequest request) throws OperationExistedException;

    /**
     * 删除模块可执行的操作
     *
     * @param id 模块id
     * @throws OperationNotFoundException 操作不存在
     * @throws OperationOccupiedException 操作被占用
     */
    void deleteOperation(Long id) throws OperationNotFoundException, OperationOccupiedException;

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
    ResourceDto createResource(SingleResourceRequest request) throws OperationNotFoundException, ResourceExistedException;

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
    ResourceDto updateResource(Long id, SingleResourceRequest request) throws ResourceNotFoundException, OperationNotFoundException, ResourceExistedException;
}
