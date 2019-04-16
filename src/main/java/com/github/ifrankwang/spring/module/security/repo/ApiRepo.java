package com.github.ifrankwang.spring.module.security.repo;

import com.github.ifrankwang.spring.interfaces.Repository;
import com.github.ifrankwang.spring.module.security.entity.ApiEntity;

import java.util.Optional;

/**
 * @author Frank Wang
 */
public interface ApiRepo extends Repository<ApiEntity, Long> {

    /**
     * 根据api路径获取api实体类对象
     *
     * @param path api路径
     * @return api实体类对象
     */
    Optional<ApiEntity> findFirstByPath(String path);

    /**
     * 根据api请求方法和路径获取api实体类对象
     *
     * @param method 请求方法
     * @param path   请求路径
     * @return api实体类对象
     */
    Optional<ApiEntity> findFirstByMethodAndPath(String method, String path);
}
