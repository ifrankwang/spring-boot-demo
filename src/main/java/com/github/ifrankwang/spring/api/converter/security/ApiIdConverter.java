package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;

/**
 * @author Frank Wang
 */
public class ApiIdConverter {

    public ApiEntity idToEntity(Long id) {
        if (null == id) {
            return null;
        }
        final ApiEntity entity = new ApiEntity();
        entity.setId(id);
        return entity;
    }
}
