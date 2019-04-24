package com.github.ifrankwang.spring.api.dto.security.user;

import lombok.Data;

/**
 * @author Frank Wang
 */
@Data
public class BaseUserDto {
    private String name;
    private String password;
    private String email;
}
