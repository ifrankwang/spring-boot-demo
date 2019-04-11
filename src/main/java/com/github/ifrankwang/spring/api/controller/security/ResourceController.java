package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.dto.security.ResourceDto;
import com.github.ifrankwang.spring.api.dto.security.SingleResourceRequest;
import com.github.ifrankwang.spring.api.facade.ResourceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_RESOURCE;
import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_SECURITY;
import static com.github.ifrankwang.spring.api.dto.AppResponse.success;
import static com.github.ifrankwang.spring.module.security.properties.SecurityConst.API_PREFIX;

/**
 * @author Frank Wang
 */
@Api(tags = {TAG_SECURITY, TAG_RESOURCE})
@RestController
@RequestMapping(API_PREFIX + "/resource")
@Transactional(rollbackFor = Exception.class)
public class ResourceController {
    private final ResourceFacade facade;

    public ResourceController(ResourceFacade facade) {
        this.facade = facade;
    }

    @ApiOperation(value = "获取全部模块列表")
    @GetMapping("/list")
    @PreAuthorize("@accessControlFacade.canAccess(authentication, #request)")
    public AppResponse<List<ResourceDto>> getResourceList(HttpServletRequest request) {
        return success(facade.getResourceList());
    }

    @ApiOperation(value = "创建一个新的资源/模块")
    @PostMapping
    public AppResponse<ResourceDto> createResource(@Validated @RequestBody SingleResourceRequest request) {
        return success(facade.createResource(request));
    }

    @ApiOperation(value = "更新模块信息")
    @PutMapping("/{id}")
    public AppResponse<ResourceDto> updateResource(@PathVariable Long id, @Validated @RequestBody SingleResourceRequest resourceRequest) {
        return success(facade.updateResource(id, resourceRequest));
    }

    @ApiOperation(value = "删除模块")
    @DeleteMapping("/{id}")
    public AppResponse deleteResource(@PathVariable Long id) {
        facade.deleteResource(id);
        return success();
    }
}
