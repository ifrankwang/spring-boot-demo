package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.query.UserQuery;
import com.github.ifrankwang.spring.module.security.repo.UserRepo;
import com.github.ifrankwang.spring.module.security.service.UserService;
import com.github.ifrankwang.spring.util.UserInfoHolder;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Frank Wang
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;
    private final UserQuery query;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo repo, UserQuery query, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.query = query;
        this.passwordEncoder = passwordEncoder;
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
    public Page<UserEntity> findAll(Pageable pageable) {
        return query.findAll(pageable);
    }

    @Override
    public UserEntity create(UserEntity entity) {
        final UserEntity operator = UserInfoHolder.getUserInfo();
        entity.setCreator(operator);
        entity.setCreateTime(LocalDateTime.now());
        entity.using(passwordEncoder).encryptPassword();
        return repo.save(entity);
    }
}
