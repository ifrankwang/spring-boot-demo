package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class OperationNotFoundException extends ServiceException {
    public OperationNotFoundException() {
        super("该模块操作不存在！");
    }
}
