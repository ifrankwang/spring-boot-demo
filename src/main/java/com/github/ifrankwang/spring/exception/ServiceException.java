package com.github.ifrankwang.spring.exception;

/**
 * 业务异常类
 *
 * @author Frank Wang
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
