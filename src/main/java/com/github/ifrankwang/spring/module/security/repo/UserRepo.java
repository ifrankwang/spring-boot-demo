package com.github.ifrankwang.spring.module.security.repo;

import com.github.ifrankwang.spring.interfaces.Repository;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;

import java.util.Optional;

/**
 * @author Frank Wang
 */
public interface UserRepo extends Repository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
