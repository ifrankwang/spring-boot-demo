package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime createTime = now();

    @OneToMany
    @JoinTable(name = "role_authority", inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<AuthorityEntity> authorities = new ArrayList<>();
}
