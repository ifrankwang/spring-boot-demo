package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.UserDto;
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
}
