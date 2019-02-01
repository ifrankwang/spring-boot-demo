package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "operation")
public class OperationEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    private String name;
    private String tag;
}
