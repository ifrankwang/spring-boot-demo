package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.LoginInfo;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.InvalidUsernameOrPasswordException;
import com.github.ifrankwang.spring.module.security.exception.LoginInfoExpiredException;
import com.github.ifrankwang.spring.module.security.exception.UserNotFoundException;

/**
 * @author Frank Wang
 */
public interface LoginFacade {
    /**
     * 用户名密码登录
     *
     * @param loginInfo 用户名密码信息
     * @return token
     */
    String login(LoginInfo loginInfo) throws UserNotFoundException, InvalidUsernameOrPasswordException;

    /**
     * token登录
     *
     * @param token 请求头中的token
     * @return 新的token
     */
    String login(String token) throws UserNotFoundException, LoginInfoExpiredException, InsufficientPermissionException;
}
