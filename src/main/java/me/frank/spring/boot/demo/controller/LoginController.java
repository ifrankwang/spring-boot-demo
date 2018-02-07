package me.frank.spring.boot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.spring.boot.demo.dto.AppResponse;
import me.frank.spring.boot.demo.service.IJwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static me.frank.spring.boot.demo.properties.SecurityConst.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "登陆相关接口")
@RestController
public class LoginController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final IJwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginController(IJwtService jwtService,
                           UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @ApiOperation(value = "登录", notes = "登陆并获取token")
    @PostMapping(LOGIN_URL)
    public AppResponse<Boolean> login() {
        // 能到controller层，说明所有校验都通过了
        return AppResponse.success(true);
    }

    @ApiOperation(
            value = "更新token",
            notes = "用token换取新token，换取失败，则说明token无效或已失效",
            produces = APPLICATION_JSON_VALUE)
    @GetMapping(API_PREFIX + "/refresh-token")
    public AppResponse<Boolean> refreshToken(HttpServletRequest request,
                                             HttpServletResponse response) {
        final String OLD_TOKEN = request.getHeader(HEADER_NAME);
        final String USER_NAME = jwtService.getSubjectFrom(OLD_TOKEN);

        // 校验用户
        userDetailsService.loadUserByUsername(USER_NAME);

        // 放入更新token
        response.setHeader(HEADER_NAME, jwtService.genTokenFor(USER_NAME));
        return AppResponse.success(true);
    }
}
