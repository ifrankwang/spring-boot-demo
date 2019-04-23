package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.GroupEntity;
import com.github.ifrankwang.spring.module.security.entity.RoleEntity;
import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.spring.module.security.exception.RoleNotFoundException;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface RoleService extends BusinessGetter {
    /**
     * 获取指定的角色实体类对象
     *
     * @param id id
     * @return 角色实体类对象
     */
    @Override
    RoleEntity findById(Long id) throws RoleNotFoundException;

    /**
     * 分页获取角色列表
     *
     * @param pageable 分页数据
     * @return 角色列表
     */
    Page<RoleEntity> findAll(Pageable pageable);

    /**
     * 获取组内角色或非组内角色
     *
     * @param pageable 分页数据
     * @param generic  是否组内角色
     * @return 组内角色或非组内角色列表
     */
    Page<RoleEntity> findByGeneric(Pageable pageable, Boolean generic);

    /**
     * 获取用户非业务类角色列表
     *
     * @param user 用户
     * @return 非业务类角色列表
     */
    List<RoleEntity> findGenericRoleOfUser(UserEntity user);

    /**
     * 获取用户业务类角色列表
     *
     * @param user  用户
     * @param group 组
     * @return 业务类角色
     */
    List<RoleEntity> findBusinessRoleOfUser(UserEntity user, GroupEntity group);

    /**
     * 创建角色
     *
     * @param entity 要创建的角色对象
     * @return 角色实体类对象
     */
    RoleEntity create(RoleEntity entity);

    /**
     * 删除角色
     *
     * @param entity 角色实体对象
     */
    void delete(RoleEntity entity);
}
