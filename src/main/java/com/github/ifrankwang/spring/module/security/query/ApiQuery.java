package com.github.ifrankwang.spring.module.security.query;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

/**
 * @author Frank Wang
 */
public interface ApiQuery {

    /**
     * 获取分页接口列表
     *
     * @param pageable 分页属性
     * @return 分页接口列表
     */
    Page<ApiEntity> findAll(Pageable pageable);
}
