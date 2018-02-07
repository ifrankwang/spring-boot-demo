package me.frank.spring.boot.demo.exception;

public class ServiceException extends RuntimeException {
    public static final ServiceException INVALID_USER = new ServiceException("该用户不存在！");
    public static final ServiceException INVALID_PASSWORD = new ServiceException("密码错误！");
    public static final ServiceException INVALID_ARGUMENTS = new ServiceException("缺少必要参数！");
    public static final ServiceException INVALID_TOKEN = new ServiceException("无效的请求！");
    public static final ServiceException INSUFFICIENT_PERMISSION = new ServiceException("权限不足！");

    public ServiceException(String message) {
        super(message);
    }
}
