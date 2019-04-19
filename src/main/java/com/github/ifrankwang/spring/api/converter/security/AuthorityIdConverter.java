package com.github.ifrankwang.spring.api.converter.security;

import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;

/**
 * @author Frank Wang
 */
public class AuthorityIdConverter {

    AuthorityEntity toEntity(Long id) {
        if (null == id) {
            return null;
        }
        final AuthorityEntity entity = new AuthorityEntity();
        entity.setId(id);
        return entity;
    }
}
