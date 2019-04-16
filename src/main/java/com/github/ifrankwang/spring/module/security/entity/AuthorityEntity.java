package com.github.ifrankwang.spring.module.security.entity;

import com.github.ifrankwang.spring.module.security.enums.Operations;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
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
    @Enumerated(STRING)
    private Operations operation;

    @ManyToOne
    private ResourceEntity resource;

    @OneToOne
    private ApiEntity api;
}
