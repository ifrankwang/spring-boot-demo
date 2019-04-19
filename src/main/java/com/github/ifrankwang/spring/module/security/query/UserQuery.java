package com.github.ifrankwang.spring.module.security.query;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import com.github.ifrankwang.utils.page.Page;
import com.github.ifrankwang.utils.page.Pageable;

/**
 * @author Frank Wang
 */
public interface UserQuery {

    /**
     * 获取所有用户
     *
     * @param pageable 分页数据
     * @return 所有用户
     */
    Page<UserEntity> findAll(Pageable pageable);
}
