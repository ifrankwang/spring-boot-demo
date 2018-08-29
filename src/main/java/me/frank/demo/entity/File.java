package me.frank.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * 文件实体类
 *
 * @author 王明哲
 */
@Data
@Entity(name = "files")
@ToString(exclude = {"creator"})
@EqualsAndHashCode(exclude = {"creator"})
public class File {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String name;
    /**
     * 文件类型(扩展名)
     */
    private String type;
    /**
     * 存储方式:oss, ftp, local, Root
     */
    private String storeMethod;
    private String storeAddress;
    private LocalDateTime createTime = now();
    private long size;

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "user_id")
    private AppUser creator;

    public String getFileUrl() {
        return this.storeAddress + this.name;
    }
}
