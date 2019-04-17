package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.facade.ApiFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_API;
import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_SECURITY;
import static com.github.ifrankwang.spring.api.dto.AppResponse.success;
import static com.github.ifrankwang.spring.module.security.properties.SecurityConst.API_PREFIX;

/**
 * @author Frank Wang
 */
@Api(tags = {TAG_SECURITY, TAG_API})
@RestController
@RequestMapping(API_PREFIX)
@Transactional(rollbackFor = Exception.class)
public class ApiController {
    private final ApiFacade facade;

    public ApiController(ApiFacade facade) {
        this.facade = facade;
    }

    @ApiOperation("更新接口信息")
    @PutMapping
    public AppResponse updateApis() {
        facade.updateApis();
        return success();
    }
}
