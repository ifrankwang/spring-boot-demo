package me.frank.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.demo.dto.AppResponse;
import me.frank.demo.dto.LoginInfo;
import me.frank.demo.entity.AppUser;
import me.frank.demo.entity.Role;
import me.frank.demo.service.JwtService;
import me.frank.demo.service.RoleService;
import me.frank.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static java.util.Collections.singletonList;
import static me.frank.demo.dto.AppResponse.success;
import static me.frank.demo.exception.ServiceException.*;
import static me.frank.demo.properties.SecurityConst.*;

/**
 * @author 王明哲
 */
@Api(tags = "登陆相关接口")
@RestController
@RequestMapping(API_PREFIX)
public class LoginController {
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public LoginController(UserService userService, RoleService roleService,
                           JwtService jwtService, PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @ApiOperation("用户名密码登陆")
    @PostMapping(LOGIN_URL)
    public AppResponse<String> login(@Validated @RequestBody LoginInfo loginInfo) {
        final AppUser user = getUserWithLoginInfo(loginInfo);
        final String token = jwtService.genTokenFor(user.getUsername());
        return success(token);
    }

    /**
     * 校验登陆信息和用户状态，可登陆的情况下返回用户实体类对象
     */
    private AppUser getUserWithLoginInfo(LoginInfo loginInfo) {
        final AppUser user = userService.findByUsername(loginInfo.getUsername()).orElseThrow(() -> INVALID_USER);
        user.using(passwordEncoder).checkIfPasswordEqualsTo(loginInfo.getPassword()).orElseThrow(INVALID_PASSWORD);
        return user;
    }

    @ApiOperation("注册")
    @PostMapping(REGISTER_URL)
    public AppResponse<String> register(@Validated @RequestBody LoginInfo loginInfo) {
        userService.findByUsername(loginInfo.getUsername()).ifPresent(user -> {
            throw USER_ALREADY_EXISTS;
        });
        final AppUser user = createNewUserByLoginInfo(loginInfo);
        final String token = jwtService.genTokenFor(user.getUsername());
        return success(token);
    }

    private AppUser createNewUserByLoginInfo(LoginInfo loginInfo) {
        final AppUser user = modelMapper.map(loginInfo, AppUser.class);
        final Role defaultRole = roleService.findDefaultRole();

        user.using(passwordEncoder).encryptPassword();
        user.setRoles(singletonList(defaultRole)).saveBy(userService);

        return user;
    }

    @ApiOperation("更新Token")
    @GetMapping("/refresh-token")
    public AppResponse<String> refreshToken(@RequestHeader(HEADER_NAME) String token) {
        final String username = jwtService.getSubjectFrom(token);
        userService.findByUsername(username).orElseThrow(() -> INVALID_TOKEN);
        final String newToken = jwtService.genTokenFor(username);
        return success(newToken);
    }
}
