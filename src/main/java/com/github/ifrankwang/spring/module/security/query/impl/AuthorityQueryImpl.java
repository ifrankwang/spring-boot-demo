package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.*;
import com.github.ifrankwang.spring.module.security.query.AuthorityQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Frank Wang
 */
@Repository
public class AuthorityQueryImpl implements AuthorityQuery {
    private final EntityManager entityManager;

    private final QAuthorityEntity qAuthority = QAuthorityEntity.authorityEntity;
    private final QRoleEntity qRole = QRoleEntity.roleEntity;
    private final QUserEntity qUser = QUserEntity.userEntity;

    public AuthorityQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AuthorityEntity> findAllByUser(UserEntity user) {
        return new JPAQuery<AuthorityEntity>(entityManager).from(qUser)
                                                           .leftJoin(qRole).on(qUser.roles.any().eq(qRole))
                                                           .rightJoin(qAuthority)
                                                           .on(qRole.authorities.any().eq(qAuthority))
                                                           .where(qUser.eq(user))
                                                           .fetch();
    }
}
