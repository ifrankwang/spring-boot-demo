package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "groups")
@EqualsAndHashCode(of = "id")
@ToString(exclude = {"users", "creator"})
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn
    private UserEntity creator;

    @ManyToMany(cascade = REFRESH)
    @JoinTable(name = "group_user", inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserEntity> users = new ArrayList<>();
}
