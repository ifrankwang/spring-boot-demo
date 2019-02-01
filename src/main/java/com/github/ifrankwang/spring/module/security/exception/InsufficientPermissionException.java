package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class InsufficientPermissionException extends ServiceException {
    public InsufficientPermissionException() {
        super("权限不足！");
    }
}
