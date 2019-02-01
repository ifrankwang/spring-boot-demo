package com.github.ifrankwang.spring.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.github.ifrankwang.spring.api.dto.AppResponse.failed;
import static org.springframework.http.HttpStatus.OK;

/**
 * 业务异常处理类
 *
 * @author Frank Wang
 */
@ControllerAdvice
public class RestExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unused")
    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
        logger.warn("\n捕获业务异常！异常信息：{}", ex.getMessage());
        return new ResponseEntity<>(failed(ex.getMessage()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleArgumentException(MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：缺少必要参数！");
        return new ResponseEntity<>(failed("缺少必要参数！"), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleArgumentException(AccessDeniedException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：请求权限不足！");
        return new ResponseEntity<>(failed("权限不足！"), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleArgumentException(Exception ex, WebRequest request) {
        logger.warn("\n捕获系统异常！{}", ex.getMessage());
        return new ResponseEntity<>(failed("系统异常！"), OK);
    }
}
