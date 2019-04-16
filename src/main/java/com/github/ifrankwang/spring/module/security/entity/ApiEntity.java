package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "api")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"authority", "creator"})
public class ApiEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String method;
    private String path;
    private String name;

    @OneToOne
    @JoinColumn
    private AuthorityEntity authority;

    @ManyToOne
    @JoinColumn
    private UserEntity creator;
}
