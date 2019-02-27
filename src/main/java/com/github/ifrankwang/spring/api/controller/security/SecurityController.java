package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationDto;
import com.github.ifrankwang.spring.api.dto.modules.security.OperationRequest;
import com.github.ifrankwang.spring.api.dto.modules.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.modules.security.SingleResourceRequest;
import com.github.ifrankwang.spring.api.facade.SecurityFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.*;
import static com.github.ifrankwang.spring.api.dto.AppResponse.success;
import static com.github.ifrankwang.spring.module.security.properties.SecurityConst.API_PREFIX;

/**
 * @author Frank Wang
 */
@Api(tags = {TAG_SECURITY})
@RestController
@RequestMapping(API_PREFIX + "security")
@Transactional(rollbackFor = Exception.class)
public class SecurityController {
    private final SecurityFacade facade;

    public SecurityController(SecurityFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "获取模块可执行的操作", tags = TAG_OPERATION)
    @GetMapping("/operation/list")
    public AppResponse<List<OperationDto>> getOperationList() {
        return success(facade.getOperationList());
    }

    @ApiOperation(value = "创建模块可执行的操作", tags = TAG_OPERATION)
    @PostMapping("/operation")
    public AppResponse<OperationDto> createOperation(@Validated @RequestBody OperationRequest request) {
        return success(facade.createOperation(request));
    }

    @ApiOperation(value = "创建一个新的资源/模块", tags = TAG_RESOURCE)
    @PostMapping("/resource")
    public AppResponse<ResourceDto> createResource(@Validated @RequestBody SingleResourceRequest request) {
        return success(facade.createResource(request));
    }
}
