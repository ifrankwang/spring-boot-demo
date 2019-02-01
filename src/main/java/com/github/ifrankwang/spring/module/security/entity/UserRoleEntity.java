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
@Entity(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity user;

    @OneToOne
    private RoleEntity role;
}
