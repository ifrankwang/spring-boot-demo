package com.github.ifrankwang.spring.module.security.exception;

import com.github.ifrankwang.spring.exception.ServiceException;

/**
 * @author Frank Wang
 */
public class ResourceExistedException extends ServiceException {
    public ResourceExistedException() {
        super("同标签名的模块已存在！");
    }
}
