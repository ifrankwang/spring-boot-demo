package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.QUserEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.query.UserQuery;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author Frank Wang
 */
@Repository
public class UserQueryImpl implements UserQuery {
    private final EntityManager entityManager;
    private final QUserEntity qUser = QUserEntity.userEntity;

    public UserQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return new Page<>(
                () -> getQuery().from(qUser).offset(pageable.getOffset()).limit(pageable.getLimit()).fetch(),
                () -> getQuery().from(qUser).fetchCount());
    }

    private JPAQuery<UserEntity> getQuery() {
        return new JPAQuery<>(entityManager);
    }
}
