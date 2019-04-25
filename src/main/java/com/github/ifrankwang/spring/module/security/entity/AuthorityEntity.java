package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "authority")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"resource", "api"})
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String tag;

    @ManyToOne(fetch = LAZY)
    private ResourceEntity resource;

    @OneToOne(fetch = LAZY)
    @JoinColumn
    private ApiEntity api;
}
