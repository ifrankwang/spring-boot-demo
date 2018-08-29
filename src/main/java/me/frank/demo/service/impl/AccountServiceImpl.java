package me.frank.demo.service.impl;

import me.frank.demo.entity.Account;
import me.frank.demo.repo.AccountRepo;
import me.frank.demo.repo.Repository;
import me.frank.demo.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 王明哲
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl implements AccountService {
    private final AccountRepo repo;

    public AccountServiceImpl(AccountRepo repo) {
        this.repo = repo;
    }

    @Override
    public Repository<Account, Long> getRepo() {
        return repo;
    }
}
