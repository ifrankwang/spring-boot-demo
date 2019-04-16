package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.exception.ApiNotFoundException;

/**
 * @author Frank Wang
 */
public interface ApiService {
    /**
     * 根据api路径获取api实体类对象
     *
     * @param path api路径
     * @return api实体类对象
     * @throws ApiNotFoundException 没有找到对应实体类对象
     */
    ApiEntity findByPath(String path) throws ApiNotFoundException;

    /**
     * 根据api请求方法和路径获取api实体类对象
     *
     * @param method 请求方法
     * @param path   请求路径
     * @return api实体类对象
     * @throws ApiNotFoundException 没有找到对应实体类对象
     */
    ApiEntity findByMethodAndPath(String method, String path) throws ApiNotFoundException;
}
