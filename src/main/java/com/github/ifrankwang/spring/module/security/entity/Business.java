package com.github.ifrankwang.spring.module.security.entity;

/**
 * @author Frank Wang
 */
public interface Business {
    Long getId();

    UserEntity getCreator();

    GroupEntity getGroup();
}
