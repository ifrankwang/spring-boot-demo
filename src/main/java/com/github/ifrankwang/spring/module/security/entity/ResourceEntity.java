package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "resource")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@ToString(exclude = {"parent", "children", "creator", "operations"})
public class ResourceEntity implements Business {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String tag;
    private LocalDateTime createTime = now();
    @Column(name = "protected")
    private Boolean protect;

    @ManyToOne
    @JoinColumn
    private UserEntity creator;

    @ManyToOne(cascade = REFRESH)
    private ResourceEntity parent;

    @OneToMany(cascade = {PERSIST, REFRESH}, orphanRemoval = true, mappedBy = "parent")
    private List<ResourceEntity> children = new ArrayList<>();

    @OneToMany(cascade = {PERSIST, REFRESH}, orphanRemoval = true, mappedBy = "resource")
    private List<AuthorityEntity> operations = new ArrayList<>();

    @Override
    public UserEntity getCreator() {
        return creator;
    }

    @Override
    public GroupEntity getGroup() {
        return null;
    }
}
