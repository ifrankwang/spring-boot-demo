package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.repo.UserRepo;
import com.github.ifrankwang.spring.module.security.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Frank Wang
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserEntity findByEmail(String email) throws UserNotFoundException {
        return repo.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserEntity findById(Long id) throws UserNotFoundException {
        return repo.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserEntity> findAll() {
        return repo.findAll();
    }
}
