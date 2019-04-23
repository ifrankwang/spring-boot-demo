package com.github.ifrankwang.spring.module.system.entity;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "system_config")
public class SystemConfigEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn
    private UserEntity systemAdmin;
}
