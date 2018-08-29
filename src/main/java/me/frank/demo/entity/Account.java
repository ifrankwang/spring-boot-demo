package me.frank.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.frank.demo.repo.Persistable;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 账户实体类
 *
 * @author 王明哲
 */
@Data
@NoArgsConstructor
@Entity(name = "accounts")
@ToString(exclude = {"owner"})
@EqualsAndHashCode(exclude = {"owner"})
public class Account implements Persistable<Account, Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    private double balance = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser owner;

    private Account(AppUser owner) {
        this.owner = owner;
    }

    public static Account newAccountOf(AppUser owner) {
        return new Account(owner);
    }
}
