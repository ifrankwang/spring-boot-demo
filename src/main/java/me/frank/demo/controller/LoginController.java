package me.frank.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.demo.dto.AppResponse;
import me.frank.demo.dto.LoginInfo;
import me.frank.demo.entity.AppUser;
import me.frank.demo.entity.Group;
import me.frank.demo.service.AccountService;
import me.frank.demo.service.GroupService;
import me.frank.demo.service.JwtService;
import me.frank.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static me.frank.demo.dto.AppResponse.success;
import static me.frank.demo.entity.Account.newAccountOf;
import static me.frank.demo.exception.ServiceException.*;
import static me.frank.demo.properties.SecurityConst.*;

/**
 * @author 王明哲
 */
@Api(tags = "登陆相关接口")
@RestController
@RequestMapping(API_PREFIX)
public class LoginController {
    private final UserService service;
    private final AccountService accountService;
    private final GroupService groupService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public LoginController(UserService service, AccountService accountService,
                           GroupService groupService, JwtService jwtService,
                           PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.service = service;
        this.accountService = accountService;
        this.groupService = groupService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @ApiOperation("用户名密码登陆")
    @PostMapping(LOGIN_URL)
    public AppResponse<String> login(@Validated @RequestBody LoginInfo loginInfo,
                                     HttpServletResponse response) {
        final AppUser user = getUserWithLoginInfo(loginInfo);
        return genTokenResponseWithUsernameAndHttpResponse(user.getUsername(), response);
    }

    /**
     * 校验登陆信息和用户状态，可登陆的情况下返回用户实体类对象
     */
    private AppUser getUserWithLoginInfo(LoginInfo loginInfo) {
        final AppUser user = service.findByUsername(loginInfo.getUsername()).orElseThrow(() -> INVALID_USER);
        user.checkIfPasswordEqualsToWithEncoder(loginInfo.getPassword(), passwordEncoder).orElseThrow(INVALID_PASSWORD);
        user.checkIfAccountExists().orElse(() -> newAccountOf(user).saveBy(accountService));
        return user;
    }

    @ApiOperation("注册")
    @PostMapping(REGISTER_URL)
    public AppResponse<String> register(@Validated @RequestBody LoginInfo loginInfo,
                                        HttpServletResponse response) {
        service.findByUsername(loginInfo.getUsername()).ifPresent(user -> {
            throw USER_ALREADY_EXISTS;
        });
        final AppUser user = createNewUserByLoginInfo(loginInfo);
        return genTokenResponseWithUsernameAndHttpResponse(user.getUsername(), response);
    }

    private AppUser createNewUserByLoginInfo(LoginInfo loginInfo) {
        final AppUser user = modelMapper.map(loginInfo, AppUser.class);
        final Group group = groupService.findCommonUserGroup()
                                        .orElseThrow(() -> USER_GROUP_NOT_EXISTS);

        user.encryptPasswordWithEncoder(passwordEncoder);
        user.setGroup(group).saveBy(service);
        user.checkIfAccountExists().orElse(() -> newAccountOf(user).saveBy(accountService));

        return user;
    }

    /**
     * 生成token回应体，会在response的headers里面加入token
     */
    private AppResponse<String> genTokenResponseWithUsernameAndHttpResponse(String subject, HttpServletResponse response) {
        final String token = jwtService.genTokenFor(subject);
        response.setHeader(HEADER_NAME, token);
        return success(token);
    }

    @ApiOperation("更新Token")
    @GetMapping("/refresh-token")
    public AppResponse<String> refreshToken(@RequestHeader(HEADER_NAME) String token,
                                            HttpServletResponse response) {
        final String username = jwtService.getSubjectFrom(token);
        service.findByUsername(username).orElseThrow(() -> INVALID_TOKEN);
        final String newToken = jwtService.genTokenFor(username);
        response.setHeader(HEADER_NAME, newToken);
        return success(newToken);
    }
}
