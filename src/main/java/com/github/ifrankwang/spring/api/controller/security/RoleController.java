package com.github.ifrankwang.spring.api.controller.security;

import com.github.ifrankwang.spring.api.dto.AppResponse;
import com.github.ifrankwang.spring.api.dto.PageRequest;
import com.github.ifrankwang.spring.api.dto.security.RoleDto;
import com.github.ifrankwang.spring.api.facade.RoleFacade;
import com.github.ifrankwang.utils.page.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_ROLE;
import static com.github.ifrankwang.spring.api.controller.ApiConstants.TAG_SECURITY;
import static com.github.ifrankwang.spring.api.dto.AppResponse.success;
import static com.github.ifrankwang.spring.module.security.properties.SecurityConst.API_PREFIX;

/**
 * @author Frank Wang
 */
@Api(tags = {TAG_SECURITY, TAG_ROLE})
@RestController
@RequestMapping(API_PREFIX + "/role")
@Transactional(rollbackFor = Exception.class)
public class RoleController {
    private final RoleFacade facade;

    public RoleController(RoleFacade facade) {
        this.facade = facade;
    }

    @ApiOperation("获取角色列表（分页）")
    @GetMapping("/list")
    public AppResponse<Page<RoleDto>> getRolePage(@Validated PageRequest request, Boolean generic) {
        return success(facade.findByGeneric(request, generic));
    }

    @ApiOperation("获取角色权限id列表")
    @GetMapping("/{id}/authority/id-list")
    public AppResponse<List<Long>> getRoleAuthorityIdList(@PathVariable Long id) {
        return success(facade.getRoleAuthorityIdList(id));
    }
}
