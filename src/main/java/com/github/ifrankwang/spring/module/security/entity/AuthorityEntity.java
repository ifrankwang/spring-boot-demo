package com.github.ifrankwang.spring.module.security.entity;

import com.github.ifrankwang.spring.module.security.enums.Operations;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "authority")
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDateTime createTime = now();
    @Enumerated(STRING)
    private Operations operation;

    @ManyToOne
    private ResourceEntity resource;

    @ManyToOne
    @JoinColumn
    private UserEntity creator;
}
