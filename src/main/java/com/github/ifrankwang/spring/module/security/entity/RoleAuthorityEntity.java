package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "role_authority")
public class RoleAuthorityEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    private RoleEntity role;

    @OneToOne
    private AuthorityEntity authority;
}
