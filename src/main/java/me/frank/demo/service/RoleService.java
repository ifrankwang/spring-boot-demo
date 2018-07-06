package me.frank.demo.service;

import me.frank.demo.entity.Role;

/**
 * @author 王明哲
 */
public interface RoleService {
    /**
     * 获取默认角色
     *
     * @return 默认角色
     */
    Role findDefaultRole();
}
