package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.api.dto.security.LoginInfo;

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
    String login(LoginInfo loginInfo);

    /**
     * token登录
     *
     * @param token 请求头中的token
     * @return 新的token
     */
    String login(String token);
}
