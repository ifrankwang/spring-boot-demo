package com.github.ifrankwang.spring.util;

import com.github.ifrankwang.spring.module.security.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Frank Wang
 */
public class UserInfoHolder {

    public static UserEntity getUserInfo() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserEntity) authentication.getDetails();
    }
}
