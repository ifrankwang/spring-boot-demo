package com.github.ifrankwang.spring.exception;

/**
 * @author Frank Wang
 */
public class IncompleteRequestArgumentsException extends ServiceException {
    public IncompleteRequestArgumentsException() {
        super("缺少必要请求参数！");
    }
}
