package me.frank.demo.entity;

import lombok.Data;
import me.frank.demo.enums.UserGender;
import me.frank.demo.util.Checkable;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static me.frank.demo.util.Checkable.of;

/**
 * 用户实体类
 * @author 王明哲
 */
@Data
@Entity(name = "users")
public class AppUser implements Persistable<AppUser, Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String username;
    private String password;
    private String nickname;
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    @ManyToMany(fetch = LAZY, cascade = REFRESH)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public AppUser setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public List<String> getRoleNames() {
        return roles.stream().map(Role::getName).collect(toList());
    }

    public List<String> getStringPermission() {
        List<String> permissionNames = emptyList();
        for (Role role : roles) {
            final List<String> permissions = role.getPermissionNames();
            permissionNames = Stream.concat(permissionNames.stream(), permissions.stream()).collect(toList());
        }
        return permissionNames;
    }

    public UserPasswordEncoder using(PasswordEncoder encoder) {
        return new UserPasswordEncoder(encoder);
    }

    public class UserPasswordEncoder {
        private PasswordEncoder encoder;

        UserPasswordEncoder(PasswordEncoder encoder) {
            this.encoder = encoder;
        }

        public Checkable checkIfPasswordEqualsTo(String password) {
            return of(encoder.matches(password, getPassword()));
        }

        public void encryptPassword() {
            setPassword(encoder.encode(getPassword()));
        }
    }
}
