package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.login.LoginInfo;
import com.github.ifrankwang.spring.api.dto.security.login.LoginResponse;
import com.github.ifrankwang.spring.module.security.exception.*;

/**
 * @author Frank Wang
 */
public interface LoginFacade {
    /**
     * 用户名密码登录
     *
     * @param loginInfo 用户名密码信息
     * @return token
     * @throws UserNotFoundException 没有找到用户
     * @throws InvalidUsernameOrPasswordException 用户名密码错误
     * @throws UserSuspendedException 用户被停用
     */
    LoginResponse login(LoginInfo loginInfo) throws UserNotFoundException, InvalidUsernameOrPasswordException, UserSuspendedException;

    /**
     * token登录
     *
     * @param token 请求头中的token
     * @return 新的token
     * @throws UserNotFoundException 没有找到用户
     * @throws LoginInfoExpiredException 登录信息过期
     * @throws InsufficientPermissionException 权限不足
     */
    String login(String token) throws UserNotFoundException, LoginInfoExpiredException, InsufficientPermissionException;
}
