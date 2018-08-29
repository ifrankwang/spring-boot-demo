package me.frank.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static me.frank.demo.dto.AppResponse.failed;
import static me.frank.demo.exception.ServiceException.*;
import static org.springframework.http.HttpStatus.OK;

/**
 * 业务异常处理类
 *
 * @author 王明哲
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
        return new ResponseEntity<>(failed(INVALID_ARGUMENTS.getMessage()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleArgumentException(AccessDeniedException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：请求权限不足！");
        return new ResponseEntity<>(failed(INSUFFICIENT_PERMISSION.getMessage()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleArgumentException(Exception ex, WebRequest request) {
        logger.warn("\n捕获系统异常！{}", ex.getMessage());
        return new ResponseEntity<>(failed(INTERNAL_SERVICE_ERROR.getMessage()), OK);
    }
}
