package me.frank.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author 王明哲
 */
@Data
@Entity(name = "groups")
@ToString(exclude = {"authorities"})
@EqualsAndHashCode(exclude = {"authorities"})
public class Group {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    @OneToMany(fetch = LAZY, cascade = REFRESH, mappedBy = "group")
    private List<Authority> authorities = new ArrayList<>();

    public Group() {
    }

    public Group(long id) {
        this.id = id;
    }
}
