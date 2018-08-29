package me.frank.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.frank.demo.repo.Persistable;
import me.frank.demo.util.Checkable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static me.frank.demo.enums.GenderType.OTHERS;
import static me.frank.demo.util.Checkable.of;

/**
 * 用户实体类
 * @author 王明哲
 */
@Data
@Entity(name = "users")
@ToString(exclude = {"account"})
@EqualsAndHashCode(exclude = {"account"})
public class AppUser implements Persistable<AppUser, Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String username;
    private String password;
    private String nickname;
    private String gender = OTHERS.getValue();

    @OneToOne(fetch = LAZY, cascade = REFRESH)
    @JoinColumn(name = "file_id")
    private File avatar;

    @ManyToOne(fetch = LAZY, cascade = REFRESH)
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToOne(fetch = LAZY, cascade = ALL, mappedBy = "owner")
    private Account account;

    public Persistable<AppUser, Long> setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return group.getAuthorities();
    }

    public Checkable checkIfPasswordEqualsToWithEncoder(String password, PasswordEncoder encoder) {
        return of(encoder.matches(password, this.password));
    }

    public void encryptPasswordWithEncoder(PasswordEncoder encoder) {
        this.password = encoder.encode(this.password);
    }

    public Checkable checkIfAccountExists() {
        return of(null != getAccount());
    }
}
