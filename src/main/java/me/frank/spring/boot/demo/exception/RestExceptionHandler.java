package me.frank.spring.boot.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static me.frank.spring.boot.demo.dto.AppResponse.failed;
import static me.frank.spring.boot.demo.exception.ServiceException.INVALID_ARGUMENTS;

@ControllerAdvice
public class RestExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unused")
    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
        LOG.warn("\n捕获业务异常！异常信息：" + ex.getMessage());
        return new ResponseEntity<>(failed(ex.getMessage()), HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleArgumentException(MethodArgumentNotValidException ex, WebRequest request) {
        LOG.warn("\n捕获异常！异常信息：缺少必要参数！");
        return new ResponseEntity<>(
                failed(INVALID_ARGUMENTS.getMessage()),
                HttpStatus.OK);
    }
}
