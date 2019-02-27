package com.github.ifrankwang.spring.api.dto.modules.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录信息类
 *
 * @author Frank Wang
 */
@Data
public class LoginInfo {
    @NotBlank
    @ApiModelProperty("用户名")
    private String email;
    @NotBlank
    @ApiModelProperty("密码")
    private String password;
}
