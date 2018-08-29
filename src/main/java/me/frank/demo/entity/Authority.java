package me.frank.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author 王明哲
 */
@Data
@Entity(name = "authorities")
@ToString(exclude = "group")
@EqualsAndHashCode(exclude = "group")
public class Authority implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String authority;

    @ManyToOne(fetch = LAZY, cascade = REFRESH)
    @JoinColumn(name = "group_id")
    private Group group;
}
