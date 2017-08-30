package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

/**
 * 对User表进行CRUD操作的Repo，
 * 会自动根据方法名生成查询sql，
 * 不需要implement
 */
public interface UserDslRepo extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {

    /**
     * 根据名称获取用户列表
     *
     * @param name 用户名称
     * @return 用户列表
     */
    List<User> findByName(String name);

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User findById(int id);
}
