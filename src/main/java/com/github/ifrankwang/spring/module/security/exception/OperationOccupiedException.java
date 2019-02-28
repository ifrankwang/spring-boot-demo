package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class OperationOccupiedException extends ServiceException {
    public OperationOccupiedException() {
        super("该操作已被占用，无法删除！");
    }
}
