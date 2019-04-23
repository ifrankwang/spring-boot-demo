package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"group", "user", "role", "creator"})
@Entity(name = "group_user")
public class GroupUserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Nullable
    @OneToOne(fetch = LAZY)
    private GroupEntity group;

    @OneToOne(fetch = LAZY)
    private UserEntity user;

    @OneToOne(fetch = LAZY)
    private RoleEntity role;

    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private UserEntity creator;
}
