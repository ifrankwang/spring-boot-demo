package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.RoleDto;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

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
}
