package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"group", "user"})
@Entity(name = "group_user")
public class GroupUserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Nullable
    @OneToOne
    private GroupEntity group;

    @OneToOne
    private UserEntity user;

    @OneToOne
    private RoleEntity role;
}
