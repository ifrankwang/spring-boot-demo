package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.query.GroupQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author Frank Wang
 */
@Repository
public class GroupQueryImpl implements GroupQuery {
    private final EntityManager entityManager;

    public GroupQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private JPAQuery<GroupEntity> getQuery() {
        return new JPAQuery<>(entityManager);
    }
}
