package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;

/**
 * @author Frank Wang
 */
public interface AuthorityService {

    /**
     * 获取api所需权限
     *
     * @param apiEntity api实体对象
     * @return 所需权限
     */
    AuthorityEntity findByApi(ApiEntity apiEntity);
}
