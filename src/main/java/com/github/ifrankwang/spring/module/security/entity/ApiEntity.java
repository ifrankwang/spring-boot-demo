package com.github.ifrankwang.spring.module.security.entity;

import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
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
@Entity(name = "api")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"authority", "creator"})
public class ApiEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Enumerated(STRING)
    private ApiMethod method;
    private String path;
    private String name;

    @OneToOne
    @JoinColumn
    private AuthorityEntity authority;

    @ManyToOne
    @JoinColumn
    private UserEntity creator;
}
