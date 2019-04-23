package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.dto.PageRequest;
import com.github.ifrankwang.spring.api.dto.security.user.BaseUserDto;
import com.github.ifrankwang.spring.api.dto.security.user.UserDto;
import com.github.ifrankwang.spring.api.facade.UserFacade;
import com.github.ifrankwang.spring.module.security.annotation.Authorize;
import com.github.ifrankwang.utils.page.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_USER;
import static com.github.ifrankwang.spring.api.dto.AppResponse.success;
import static com.github.ifrankwang.spring.module.security.properties.SecurityConst.API_PREFIX;

/**
 * @author Frank Wang
 */
@Api(tags = TAG_USER)
@RestController
@RequestMapping(API_PREFIX + "/user")
public class UserController {
    private final UserFacade facade;

    public UserController(UserFacade facade) {
        this.facade = facade;
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    @Authorize
    public AppResponse<Page<UserDto>> getUserList(@Validated PageRequest request) {
        return success(facade.getUserList(request));
    }

    @ApiOperation("创建用户")
    @PostMapping
    @Authorize
    public AppResponse<UserDto> createUser(@Validated BaseUserDto request) {
        return success(facade.createUser(request));
    }
}
