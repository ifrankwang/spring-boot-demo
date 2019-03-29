package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class ApiNotFoundException extends ServiceException {
    public ApiNotFoundException() {
        super("未记录该api路径！");
    }
}
