package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.facade.UserFacade;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_USER;
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
}
