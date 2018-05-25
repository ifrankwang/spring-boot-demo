package me.frank.demo.repo;

import me.frank.demo.entity.AppUser;

import java.util.Optional;

/**
 * 对User表进行操作的Repo
 * @author 王明哲
 */
public interface UserRepo extends Repository<AppUser, Long> {

    /**
     * 根据id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    Optional<AppUser> findById(long id);

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    Optional<AppUser> findByUsername(String username);
}
