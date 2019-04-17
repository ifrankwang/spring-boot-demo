package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

import java.util.Optional;

/**
 * @author Frank Wang
 */
public interface ApiService {
    /**
     * 创建新的接口实体类对象
     *
     * @param entity 要创建的接口实体类对象
     * @return 已创建的接口实体类对象
     */
    ApiEntity create(ApiEntity entity);

    /**
     * 获取分页接口列表
     * @param pageable 分页属性
     * @return 分页接口列表
     */
    Page<ApiEntity> findAll(Pageable pageable);

    /**
     * 根据api请求方法和路径获取api实体类对象
     *
     * @param method 请求方法
     * @param path   请求路径
     * @return api实体类对象
     * @throws ApiNotFoundException 没有找到对应实体类对象
     */
    ApiEntity findByMethodAndPath(ApiMethod method, String path) throws ApiNotFoundException;

    /**
     * 根据api请求方法和路径获取api实体类对象
     *
     * @param method 请求方法
     * @param path   请求路径
     * @return api实体类对象
     * @throws ApiNotFoundException 没有找到对应实体类对象
     */
    Optional<ApiEntity> findOptionalByMethodAndPath(ApiMethod method, String path);
}
