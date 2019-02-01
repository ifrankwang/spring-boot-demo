package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class InvalidUsernameOrPasswordException extends ServiceException {
    public InvalidUsernameOrPasswordException() {
        super("用户名或密码不正确！");
    }
}
