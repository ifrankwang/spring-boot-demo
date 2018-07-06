package me.frank.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author 王明哲
 */
@Data
@Entity(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private String description;

    @ManyToMany(fetch = LAZY, cascade = REFRESH)
    @JoinTable(name = "roles_permissions",
               joinColumns = @JoinColumn(name = "role_id"),
               inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions = emptyList();

    public List<String> getPermissionNames() {
        return permissions.stream().map(Permission::getPermission).collect(toList());
    }
}
