package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "resource_operation")
public class ResourceOperationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    private ResourceEntity resource;

    @OneToOne
    private OperationEntity operation;
}
