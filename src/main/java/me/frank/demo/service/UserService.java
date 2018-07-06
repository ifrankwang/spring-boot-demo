package me.frank.demo.service;

import me.frank.demo.entity.AppUser;

import java.util.Optional;

/**
 * 用户相关服务接口
 *
 * @author 王明哲
 */
public interface UserService extends RepoAvailable<AppUser, Long> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    Optional<AppUser> findByUsername(String username);
}
