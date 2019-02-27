package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class OperationExistedException extends ServiceException {
    public OperationExistedException() {
        super("同标签名的操作已存在！");
    }
}
