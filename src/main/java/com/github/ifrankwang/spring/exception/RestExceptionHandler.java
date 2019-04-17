package com.github.ifrankwang.spring.exception;

import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
        return new ResponseEntity<>(failed(new IncompleteRequestArgumentsException()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<Object> handleMissingParamException(MissingServletRequestParameterException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：{}", ex.getMessage());
        return new ResponseEntity<>(failed(new IncompleteRequestArgumentsException()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleMissingParamException(HttpMessageNotReadableException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：{}", ex.getMessage());
        return new ResponseEntity<>(failed(new IncompleteRequestArgumentsException()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Object> handleArgumentException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：不支持的请求方式！");
        return new ResponseEntity<>(failed(new InvalidRequestMethodException()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Object> handleAuthorityException(AccessDeniedException ex, WebRequest request) {
        logger.warn("\n捕获异常！异常信息：请求权限不足！");
        return new ResponseEntity<>(failed(new InsufficientPermissionException()), OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleSystemException(Exception ex, WebRequest request) {
        logger.error("\n捕获系统异常！", ex);
        return new ResponseEntity<>(failed(new InternalServerError()), OK);
    }
}
