package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.ApiEntity;
import com.github.ifrankwang.spring.module.security.entity.QApiEntity;
import com.github.ifrankwang.spring.module.security.query.ApiQuery;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * @author Frank Wang
 */
@Repository
public class ApiQueryImpl implements ApiQuery {
    private final EntityManager entityManager;

    private final QApiEntity qApi = QApiEntity.apiEntity;

    public ApiQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<ApiEntity> findAll(Pageable pageable) {
        return new Page<>(
                () -> getQuery().from(qApi)
                                .orderBy(qApi.path.asc(), qApi.method.asc())
                                .limit(pageable.getLimit()).offset(pageable.getOffset()).fetch(),
                () -> getQuery().from(qApi).fetchCount());
    }

    private JPAQuery<ApiEntity> getQuery() {
        return new JPAQuery<>(entityManager);
    }
}
