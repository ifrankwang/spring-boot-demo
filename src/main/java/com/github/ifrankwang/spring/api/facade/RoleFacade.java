package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.role.BaseRoleDto;
import com.github.ifrankwang.spring.api.dto.security.role.RoleDto;
import com.github.ifrankwang.spring.module.security.exception.RoleNotFoundException;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

import java.util.List;

/**
 * @author Frank Wang
 */
public interface RoleFacade {

    /**
     * 分页获取角色列表
     *
     * @param pageable 分页数据
     * @return 角色列表
     */
    Page<RoleDto> findAll(Pageable pageable);

    /**
     * 获取组内角色或非组内角色
     *
     * @param pageable 分页数据
     * @param generic  是否组内角色
     * @return 组内角色或非组内角色列表
     */
    Page<RoleDto> findByGeneric(Pageable pageable, Boolean generic);

    /**
     * 获取角色的权限id列表
     *
     * @param roleId 角色id
     * @return 角色的权限id列表
     * @throws RoleNotFoundException 没有找到角色
     */
    List<Long> getRoleAuthorityIdList(Long roleId) throws RoleNotFoundException;

    /**
     * 更新角色的权限id列表
     *
     * @param roleId          角色id
     * @param authorityIdList 角色的权限id列表
     * @throws RoleNotFoundException 没有找到角色
     */
    void updateRoleAuthorityIdList(Long roleId, List<Long> authorityIdList) throws RoleNotFoundException;

    /**
     * 新建角色
     *
     * @param baseRoleDto 请求参数
     * @return 新创建的角色
     */
    RoleDto createRole(BaseRoleDto baseRoleDto);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @throws RoleNotFoundException 没找到角色
     */
    void deleteRole(Long roleId) throws RoleNotFoundException;

    /**
     * 更新角色信息
     *
     * @param roleId      角色ID
     * @param baseRoleDto 更新信息
     * @return 更新后的对象
     * @throws RoleNotFoundException 没找到角色
     */
    RoleDto updateRole(Long roleId, BaseRoleDto baseRoleDto) throws RoleNotFoundException;
}
