package me.frank.spring.boot.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.spring.boot.demo.dto.AppResponse;
import me.frank.spring.boot.demo.exception.ServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static me.frank.spring.boot.demo.properties.SecurityConst.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(tags = "暂无归类接口")
@RestController
public class BaseController {

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "测试接口",
            notes = "无需权限<br/>" +
                    "返回数据：Hello world!",
            response = String.class)
    @RequestMapping(value = NO_AUTH_URL + "/test", method = {GET, POST})
    public AppResponse<String> noAuthTest() {
        return AppResponse.success("Hello world!");
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "测试接口",
            notes = "需要权限访问<br/>" +
                    "返回数据：Hello World!",
            response = String.class)
    @RequestMapping(value = API_PREFIX + "/test", method = {GET, POST})
    public AppResponse<String> authTest(Authentication authentication) {
        return AppResponse.success("Hello " + authentication.getName() + "!");
    }

    @ApiOperation(
            produces = APPLICATION_JSON_VALUE,
            value = "异常接口",
            notes = "不做调用，校验Token异常将转向此接口")
    @RequestMapping(value = AUTH_FAILED_URL)
    public AppResponse error(HttpServletRequest request) {
        final ServiceException ERROR = (ServiceException) request.getAttribute(ATTR_ERROR);
        return AppResponse.failed(ERROR.getMessage());
    }
}
