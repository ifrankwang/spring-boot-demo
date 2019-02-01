package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.dto.security.LoginInfo;
import com.github.ifrankwang.spring.api.service.LoginService;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_LOGIN;
import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_SECURITY;
import static com.github.ifrankwang.spring.api.dto.AppResponse.failed;
import static com.github.ifrankwang.spring.api.dto.AppResponse.success;

/**
 * @author Frank Wang
 */
@Api(tags = {TAG_SECURITY, TAG_LOGIN})
@RestController
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation("用户名密码登录")
    @PostMapping(SecurityConst.LOGIN_URL)
    public AppResponse<String> login(@Validated @RequestBody LoginInfo loginInfo) {
        return success(loginService.login(loginInfo));
    }

    @ApiOperation("更新Token")
    @GetMapping(SecurityConst.TOKEN_URL)
    public AppResponse<String> refreshToken(@ApiParam(hidden = true) @RequestHeader(SecurityConst.HEADER_NAME) String token) {
        return success(loginService.login(token));
    }

    @ApiOperation(value = "异常接口，不做调用，校验Token异常将转向此接口", hidden = true)
    @RequestMapping(SecurityConst.AUTH_FAILED_URL)
    public AppResponse<String> errorOccur() {
        return failed(new InsufficientPermissionException().getMessage());
    }
}
