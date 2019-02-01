package com.github.ifrankwang.spring.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Frank Wang
 */
@NoRepositoryBean
public interface Repository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
}