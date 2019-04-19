package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class RoleNotFoundException extends ServiceException {
    public RoleNotFoundException() {
        super("该角色不存在！");
    }
}
