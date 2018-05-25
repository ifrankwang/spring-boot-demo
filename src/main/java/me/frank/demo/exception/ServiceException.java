package me.frank.demo.exception;

/**
 * 业务异常类
 *
 * @author 王明哲
 */
public class ServiceException extends RuntimeException {
    public static final ServiceException INVALID_USER = new ServiceException("该用户不存在！");
    public static final ServiceException INVALID_PASSWORD = new ServiceException("密码错误！");
    public static final ServiceException INVALID_ARGUMENTS = new ServiceException("缺少必要参数！");
    public static final ServiceException INVALID_TOKEN = new ServiceException("无效的请求！");
    public static final ServiceException INSUFFICIENT_PERMISSION = new ServiceException("权限不足！");
    public static final ServiceException INSUFFICIENT_BALANCE = new ServiceException("用户账户余额不足！");
    public static final ServiceException USER_ALREADY_EXISTS = new ServiceException("用户已注册！");
    public static final ServiceException USER_GROUP_NOT_EXISTS = new ServiceException("该用户组不存在！");
    public static final ServiceException INTERNAL_SERVICE_ERROR = new ServiceException("系统异常！");

    public ServiceException(String message) {
        super(message);
    }
}
