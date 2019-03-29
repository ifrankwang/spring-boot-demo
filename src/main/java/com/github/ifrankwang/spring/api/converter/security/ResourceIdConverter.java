package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;

/**
 * @author Frank Wang
 */
public class ResourceIdConverter {

    public ResourceEntity resourceIdToEntity(Long id) {
        if (null != id) {
            final ResourceEntity entity = new ResourceEntity();
            entity.setId(id);
            return entity;
        }
        return null;
    }
}
