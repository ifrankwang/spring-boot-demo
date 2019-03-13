package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.facade.UserFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public AppResponse<List> getUserList() {
        return success(facade.getUserList());
    }
}
