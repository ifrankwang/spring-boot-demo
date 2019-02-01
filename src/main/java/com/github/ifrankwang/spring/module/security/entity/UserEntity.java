package com.github.ifrankwang.spring.module.security.entity;

import com.github.ifrankwang.spring.module.security.query.AuthorityQuery;
import com.github.ifrankwang.utils.password.PasswordHolder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
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

    @OneToMany
    @JoinTable(name = "user_role", inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEntity> roles = new ArrayList<>();

    @Transient
    private AuthorityQuery authorityQuery;

    public List<AuthorityEntity> getAuthorities() {
        return authorityQuery.findAllByUser(this);
    }
}
