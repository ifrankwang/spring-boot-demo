package com.github.ifrankwang.spring.module.security.entity;

import com.github.ifrankwang.spring.module.security.enums.AccessLevel;
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
@Entity(name = "role_authority")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"role", "authority"})
public class RoleAuthorityEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Enumerated(STRING)
    private AccessLevel accessLevel;

    @OneToOne
    private RoleEntity role;

    @OneToOne
    private AuthorityEntity authority;
}
