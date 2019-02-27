package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.module.security.exception.OperationExistedException;
import com.github.ifrankwang.spring.module.security.exception.ResourceExistedException;

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
     * 创建一个新的资源/模块
     *
     * @param request 请求体
     * @return 详细信息的资源/模块对象
     * @throws ResourceExistedException 同标签名模块已存在
     */
    ResourceDto createResource(SingleResourceRequest request) throws ResourceExistedException;
}
