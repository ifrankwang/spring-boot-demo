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
@ToString(exclude = {"parent", "children"})
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String tag;
    private LocalDateTime createTime = now();

    @ManyToOne(cascade = REFRESH)
    private ResourceEntity parent;

    @OneToMany(cascade = {PERSIST, REFRESH})
    @JoinTable(name = "resource_operation", inverseJoinColumns = @JoinColumn(name = "operation_id"))
    private List<OperationEntity> operations = new ArrayList<>();

    @OneToMany(cascade = {PERSIST, REFRESH}, orphanRemoval = true)
    @JoinTable(name = "resource", joinColumns = @JoinColumn(name = "parent_id"),
               inverseJoinColumns = @JoinColumn(name = "id"))
    private List<ResourceEntity> children = new ArrayList<>();

    public ResourceEntity(Long id) {
        this.id = id;
    }
}
