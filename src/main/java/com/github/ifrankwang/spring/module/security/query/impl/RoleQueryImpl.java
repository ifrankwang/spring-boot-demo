package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.entity.QGroupUserEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.query.RoleQuery;
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
    private final QGroupUserEntity qGroupUser = QGroupUserEntity.groupUserEntity;

    public RoleQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
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
