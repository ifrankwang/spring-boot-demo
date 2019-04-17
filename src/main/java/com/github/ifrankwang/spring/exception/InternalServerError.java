package com.github.ifrankwang.spring.exception;

/**
 * @author Frank Wang
 */
public class InternalServerError extends ServiceException {
    public InternalServerError() {
        super("系统异常！");
    }
}
