package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class UserSuspendedException extends ServiceException {
    public UserSuspendedException() {
        super("用户已被停用！请联系管理员！");
    }
}
