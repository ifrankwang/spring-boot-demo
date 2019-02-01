package com.github.ifrankwang.spring.module.security.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Frank Wang
 */
@Data
@Entity(name = "resource")
@EqualsAndHashCode(of = "id")
@ToString(exclude = "parent")
public class ResourceEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private String tag;
    private LocalDateTime createTime = now();

    @OneToMany
    @JoinTable(name = "resource_operation", inverseJoinColumns = @JoinColumn(name = "operation_id"))
    private List<OperationEntity> operations = new ArrayList<>();

    @ManyToOne
    private ResourceEntity parent;

    @OneToMany
    private List<ResourceEntity> children;
}
