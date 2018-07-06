package me.frank.demo.entity;

import lombok.Data;
import me.frank.demo.enums.PermissionOperation;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author 王明哲
 */
@Data
@Entity(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private PermissionOperation operation;

    public String getPermission() {
        return name + ":" + operation;
    }
}
