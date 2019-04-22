package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.ApiDto;
import com.github.ifrankwang.spring.exception.InternalServerError;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

/**
 * @author Frank Wang
 */
public interface ApiFacade {

    /**
     * 更新api列表
     *
     * @throws InternalServerError 系统错误
     */
    void updateApis() throws InternalServerError;

    /**
     * 获取分页接口列表
     *
     * @param pageable 分页属性
     * @return 分页接口列表
     */
    Page<ApiDto> getApiPage(Pageable pageable);

    /**
     * 删除api信息
     *
     * @param id api id
     * @throws ApiNotFoundException 没有找到对应的api信息
     */
    void deleteApi(Long id) throws ApiNotFoundException;
}
