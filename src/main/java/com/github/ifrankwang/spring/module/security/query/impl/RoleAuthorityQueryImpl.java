package com.github.ifrankwang.spring.module.security.query.impl;

import com.github.ifrankwang.spring.module.security.entity.AuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.QRoleAuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleAuthorityEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.query.RoleAuthorityQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author Frank Wang
 */
@Repository
public class RoleAuthorityQueryImpl implements RoleAuthorityQuery {
    private final EntityManager entityManager;
    private final QRoleAuthorityEntity qRoleAuthority = QRoleAuthorityEntity.roleAuthorityEntity;

    public RoleAuthorityQueryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<AuthorityEntity> findByRoles(List<RoleEntity> roles) {
        return getQuery().select(qRoleAuthority.authority).distinct()
                         .from(qRoleAuthority)
                         .where(qRoleAuthority.role.in(roles))
                         .fetch();
    }

    private JPAQuery<RoleAuthorityEntity> getQuery() {
        return new JPAQuery<>(entityManager);
    }
}
