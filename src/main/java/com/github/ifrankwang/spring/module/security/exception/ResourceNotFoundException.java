package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class ResourceNotFoundException extends ServiceException {
    public ResourceNotFoundException() {
        super("该模块不存在！");
    }
}
