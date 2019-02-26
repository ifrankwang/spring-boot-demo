package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.security.SingleResourceRequest;

/**
 * @author Frank Wang
 */
public interface SecurityFacade {
    /**
     * 创建一个新的资源/模块
     *
     * @param request 请求体
     * @return 详细信息的资源/模块对象
     */
    ResourceDto createResource(SingleResourceRequest request);
}
