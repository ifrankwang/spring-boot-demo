package me.frank.demo.service;

import me.frank.demo.entity.Group;

import java.util.Optional;

/**
 * @author 王明哲
 */
public interface GroupService extends RepoAvailable<Group, Long> {

    /**
     * 获取普通用户组
     *
     * @return 普通用户组实体类对象
     */
    Optional<Group> findCommonUserGroup();
}
