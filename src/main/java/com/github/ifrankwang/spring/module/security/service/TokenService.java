package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.LoginInfoExpiredException;

/**
 * JWT相关服务接口
 *
 * @author Frank Wang
 */
public interface TokenService {

    /**
     * 为指定数据生成Token
     * @param subject 指定数据内容
     * @return Token
     */
    String genTokenFor(String subject);

    /**
     * 从Token中解密获取数据
     * @param token Token
     * @return 解密后的数据
     * @throws LoginInfoExpiredException token失效
     * @throws InsufficientPermissionException 错误的token
     */
    String getSubjectFrom(String token) throws LoginInfoExpiredException, InsufficientPermissionException;
}
