package me.frank.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登陆信息类
 *
 * @author 王明哲
 */
@Data
public class LoginInfo {
    @NotBlank
    @ApiModelProperty("用户名")
    private String username;
    @NotBlank
    @ApiModelProperty("密码")
    private String password;
}
