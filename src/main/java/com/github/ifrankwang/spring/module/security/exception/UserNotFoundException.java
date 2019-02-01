package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class UserNotFoundException extends ServiceException {
    public UserNotFoundException() {
        super("该用户不存在！");
    }
}
