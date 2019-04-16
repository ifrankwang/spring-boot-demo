package com.github.ifrankwang.spring.module.security.entity;

import javax.annotation.Nullable;

/**
 * @author Frank Wang
 */
public interface Business {
    Long getId();

    UserEntity getCreator();

    @Nullable
    GroupEntity getGroup();
}
