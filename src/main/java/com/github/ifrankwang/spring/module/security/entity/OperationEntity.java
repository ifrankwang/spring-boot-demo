package com.github.ifrankwang.spring.module.security.entity;

import lombok.AccessLevel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "operation")
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String tag;
    @Enumerated(STRING)
    private AccessLevel accessLevel = PRIVATE;
}
