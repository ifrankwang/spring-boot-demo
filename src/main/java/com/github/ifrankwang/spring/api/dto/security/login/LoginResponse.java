package com.github.ifrankwang.spring.api.dto.security.login;

import com.github.ifrankwang.spring.api.dto.security.user.UserDto;
import lombok.Data;

import java.util.List;

/**
 * @author Frank Wang
 */
@Data
public class LoginResponse {
    private String token;
    private UserDto userInfo;
    private List<Long> authorities;
}
