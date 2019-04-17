package com.github.ifrankwang.spring.exception;

/**
 * @author Frank Wang
 */
public class InvalidRequestArgumentsException extends ServiceException {
    public InvalidRequestArgumentsException() {
        super("请求参数有误！");
    }
}
