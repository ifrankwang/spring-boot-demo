package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class LoginInfoExpiredException extends ServiceException {
    public LoginInfoExpiredException() {
        super("登录信息已过期！请重新登录！");
    }
}
