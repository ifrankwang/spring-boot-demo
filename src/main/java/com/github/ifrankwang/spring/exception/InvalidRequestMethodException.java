package com.github.ifrankwang.spring.exception;

/**
 * @author Frank Wang
 */
public class InvalidRequestMethodException extends ServiceException {
    public InvalidRequestMethodException() {
        super("请求方式错误！");
    }
}
