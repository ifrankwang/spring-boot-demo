package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.*;
import com.github.ifrankwang.spring.module.security.query.RoleQuery;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Frank Wang
 */
@Repository
public class RoleQueryImpl implements RoleQuery {
    private final EntityManager entityManager;
    private final QRoleEntity qRole = QRoleEntity.roleEntity;
    private final QGroupUserEntity qGroupUser = QGroupUserEntity.groupUserEntity;

    public RoleQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<RoleEntity> findAll(Pageable pageable) {
        return new Page<>(
                () -> getQuery().from(qRole).offset(pageable.getOffset()).limit(pageable.getLimit()).fetch(),
                () -> getQuery().from(qRole).fetchCount());
    }

    @Override
    public Page<RoleEntity> findByGeneric(Pageable pageable, Boolean generic) {
        final BooleanExpression genericCondition = null == generic ? qRole.generic.isFalse() : qRole.generic.isTrue();
        return new Page<>(
                () -> getQuery().from(qRole).where(genericCondition).offset(pageable.getOffset())
                                .limit(pageable.getLimit()).fetch(),
                () -> getQuery().from(qRole).where(genericCondition).fetchCount()
        );
    }

    @Override
    public List<RoleEntity> findGenericRoleOfUser(UserEntity user) {
        return getQuery().select(qGroupUser.role).from(qGroupUser)
                         .where(qGroupUser.user.eq(user),
                                qGroupUser.role.generic.isTrue(),
                                qGroupUser.group.isNull())
                         .fetch();
    }

    @Override
    public List<RoleEntity> findBusinessRoleOfUser(UserEntity user, GroupEntity group) {
        return getQuery().select(qGroupUser.role).from(qGroupUser)
                         .where(qGroupUser.user.eq(user),
                                qGroupUser.role.generic.isFalse(),
                                qGroupUser.group.eq(group))
                         .fetch();
    }

    private JPAQuery<RoleEntity> getQuery() {
        return new JPAQuery<>(entityManager);
    }
}
