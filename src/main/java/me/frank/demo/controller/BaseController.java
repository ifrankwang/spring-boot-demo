package me.frank.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.frank.demo.dto.AppResponse;
import me.frank.demo.exception.ServiceException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static me.frank.demo.dto.AppResponse.failed;
import static me.frank.demo.dto.AppResponse.success;
import static me.frank.demo.properties.SecurityConst.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author 王明哲
 */
@Api(tags = "测试类接口")
@RestController
public class BaseController {

    @ApiOperation(value = "测试接口",
                  notes = "无需权限<br/>" +
                          "返回数据：Hello world!")
    @RequestMapping(value = API_PREFIX + NO_AUTH_URL + "/test", method = {GET, POST})
    public AppResponse<String> noAuthTest() {
        return success("Hello world!");
    }

    @ApiOperation(value = "测试接口",
                  notes = "需要管理员权限访问<br/>" +
                          "返回数据：Hello ${userName}!")
    @GetMapping(value = API_PREFIX + "/admin/test")
    public AppResponse<String> authAdminTest() {
        return success("Hello world!");
    }

    @ApiOperation(value = "测试接口",
                  notes = "需要普通用户权限访问<br/>" +
                          "返回数据：Hello ${userName}!")
    @GetMapping(value = API_PREFIX + "/user/test")
    public AppResponse<String> authUserTest() {
        return success("Hello world!");
    }

    @ApiOperation(value = "异常接口，不做调用，校验Token异常将转向此接口",
                  hidden = true)
    @RequestMapping(AUTH_FAILED_URL)
    public AppResponse<String> errorOccur(HttpServletRequest request) {
        final ServiceException error = (ServiceException) request.getAttribute(ATTR_ERROR);
        return failed(error.getMessage());
    }
}
