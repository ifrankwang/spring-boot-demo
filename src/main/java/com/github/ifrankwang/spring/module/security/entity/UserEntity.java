package com.github.ifrankwang.spring.module.security.entity;

import com.github.ifrankwang.utils.password.PasswordHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@EqualsAndHashCode(of = "id")
@Entity(name = "user")
public class UserEntity implements PasswordHolder {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createTime = now();
    private Boolean enabled = true;
}
