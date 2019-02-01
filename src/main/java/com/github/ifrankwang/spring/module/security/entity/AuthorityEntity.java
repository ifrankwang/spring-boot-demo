package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "authority")
public class AuthorityEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDateTime createTime;

    @ManyToOne
    private ResourceEntity resource;

    @ManyToOne
    private OperationEntity operation;

    @Override
    public String getAuthority() {
        return String.format("%s-%s:%s", resource.getId(), resource.getTag(), operation.getTag());
    }
}
