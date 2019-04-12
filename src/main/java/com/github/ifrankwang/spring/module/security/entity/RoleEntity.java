package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"authorities"})
@Entity(name = "role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Boolean generic = false;
    private LocalDateTime createTime = now();

    @ManyToOne
    @JoinColumn
    private UserEntity creator;

    @OneToMany
    @JoinTable(name = "role_authority", inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<AuthorityEntity> authorities = new ArrayList<>();
}
