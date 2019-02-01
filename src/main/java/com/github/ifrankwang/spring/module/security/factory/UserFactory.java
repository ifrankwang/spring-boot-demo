package com.github.ifrankwang.spring.module.security.factory;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.spring.module.security.query.AuthorityQuery;
import com.github.ifrankwang.spring.module.security.repo.UserRepo;
import org.springframework.stereotype.Component;

/**
 * @author Frank Wang
 */
@Component
public class UserFactory {
    private final UserRepo repo;
    private final AuthorityQuery authorityQuery;

    public UserFactory(UserRepo repo, AuthorityQuery authorityQuery) {
        this.repo = repo;
        this.authorityQuery = authorityQuery;
    }

    public UserEntity getByEmail(String email) throws UserNotFoundException {
        return repo.findByEmail(email).map(userEntity -> {
            userEntity.setAuthorityQuery(authorityQuery);
            return userEntity;
        }).orElseThrow(UserNotFoundException::new);
    }
}
