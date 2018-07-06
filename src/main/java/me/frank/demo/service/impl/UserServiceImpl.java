package me.frank.demo.service.impl;

import me.frank.demo.entity.AppUser;
import me.frank.demo.repo.Repository;
import me.frank.demo.repo.UserRepo;
import me.frank.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author 王明哲
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public Repository<AppUser, Long> getRepo() {
        return repo;
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
