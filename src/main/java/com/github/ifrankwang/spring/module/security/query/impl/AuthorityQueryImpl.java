package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.query.AuthorityQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author Frank Wang
 */
@Repository
public class AuthorityQueryImpl implements AuthorityQuery {
    private final EntityManager entityManager;

    public AuthorityQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
