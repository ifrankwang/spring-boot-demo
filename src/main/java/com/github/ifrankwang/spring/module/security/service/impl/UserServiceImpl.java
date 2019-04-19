package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.query.UserQuery;
import com.github.ifrankwang.spring.module.security.repo.UserRepo;
import com.github.ifrankwang.spring.module.security.service.UserService;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Frank Wang
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo repo;
    private final UserQuery query;

    public UserServiceImpl(UserRepo repo, UserQuery query) {
        this.repo = repo;
        this.query = query;
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
}
