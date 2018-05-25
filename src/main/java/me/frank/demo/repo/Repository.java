package me.frank.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author 王明哲
 */
@NoRepositoryBean
public interface Repository<T, ID> extends JpaRepository<T, ID>, QuerydslPredicateExecutor<T> {
}
