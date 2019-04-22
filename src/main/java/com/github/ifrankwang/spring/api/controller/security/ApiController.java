package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.dto.PageRequest;
import com.github.ifrankwang.spring.api.dto.security.ApiDto;
import com.github.ifrankwang.spring.api.facade.ApiFacade;
import com.github.ifrankwang.spring.module.security.annotation.Authorize;
import com.github.ifrankwang.spring.module.security.annotation.BusinessAuthorize;
import com.github.ifrankwang.spring.module.security.service.ApiService;
import com.github.ifrankwang.utils.page.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Authorize
    public AppResponse updateApis() {
        facade.updateApis();
        return success();
    }

    @ApiOperation("获取接口列表（分页）")
    @GetMapping("/list")
    @Authorize
    public AppResponse<Page<ApiDto>> getApiPage(@Validated PageRequest request) {
        return success(facade.getApiPage(request));
    }

    @ApiOperation("删除接口信息")
    @DeleteMapping("/{id}")
    @BusinessAuthorize(id = "#id", getterClass = ApiService.class)
    public AppResponse deleteApi(@PathVariable Long id) {
        facade.deleteApi(id);
        return success();
    }
}
