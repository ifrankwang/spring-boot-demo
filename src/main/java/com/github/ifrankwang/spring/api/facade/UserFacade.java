package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.user.BaseUserDto;
import com.github.ifrankwang.spring.api.dto.security.user.UserDto;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

/**
 * @author Frank Wang
 */
public interface UserFacade {

    /**
     * 获取用户列表
     *
     * @param pageable 分页数据
     * @return 用户列表
     */
    Page<UserDto> getUserList(Pageable pageable);

    /**
     * 创建用户
     *
     * @param baseUserDto 创建信息
     * @return 新建的用户
     */
    UserDto createUser(BaseUserDto baseUserDto);

    /**
     * 停用用户
     *
     * @param userId 用户id
     * @throws UserNotFoundException 没有找到用户
     */
    void suspendOrActiveUser(Long userId) throws UserNotFoundException;

    /**
     * 更新用户信息
     *
     * @param userId      用户id
     * @param baseUserDto 更新信息
     * @return 更新后的用户信息
     * @throws UserNotFoundException 没有找到用户
     */
    UserDto updateUser(Long userId, BaseUserDto baseUserDto) throws UserNotFoundException;

    /**
     * 删除用户（只是把用户停用）
     *
     * @param userId 用户id
     * @throws UserNotFoundException 没找到用户
     */
    void deleteUser(Long userId) throws UserNotFoundException;
}
