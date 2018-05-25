package me.frank.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.frank.demo.repo.Persistable;
import me.frank.demo.repo.PersistableGroup;
import me.frank.demo.util.Checkable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.List;

import static java.util.Arrays.asList;
import static javax.persistence.GenerationType.IDENTITY;
import static me.frank.demo.exception.ServiceException.INSUFFICIENT_BALANCE;
import static me.frank.demo.util.Checkable.of;

/**
 * 账户实体类
 *
 * @author 王明哲
 */
@Data
@Entity(name = "accounts")
@ToString(exclude = {"user"})
@EqualsAndHashCode(exclude = {"user"})
public class Account implements Persistable<Account, Long> {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    private double balance = 0.0;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Account() {
    }

    public Account(AppUser user) {
        this.user = user;
    }

    public PersistableGroup<Account, Long> transferToAccountByAmount(Account account, double amount) {
        consume(amount);
        account.recharge(amount);
        return new AccountGroup(asList(this, account));
    }

    public PersistableGroup<Account, Long> transferToAccountByAmountWithFeeRate(Account account,
                                                                                double amount,
                                                                                float feeRate) {
        consume(amount);
        account.recharge(amount * (1 - feeRate));
        return new AccountGroup(asList(this, account));
    }

    public Persistable<Account, Long> consume(double amount) {
        checkIfBalanceIsSufficient(amount).orElseThrow(INSUFFICIENT_BALANCE);
        balance -= amount;
        return this;
    }

    public Persistable<Account, Long> recharge(double amount) {
        balance += amount;
        return this;
    }

    public Checkable checkIfBalanceIsSufficient(double amount) {
        return of(balance >= amount);
    }

    public static class AccountGroup implements PersistableGroup<Account, Long> {
        List<Account> accounts;

        public AccountGroup(List<Account> accounts) {
            this.accounts = accounts;
        }

        @Override
        public List<Account> saveAllBy(JpaRepository<Account, Long> repository) {
            return repository.saveAll(accounts);
        }
    }
}
