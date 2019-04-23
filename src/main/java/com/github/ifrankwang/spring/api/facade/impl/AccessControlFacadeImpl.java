package com.github.ifrankwang.spring.api.facade.impl;

import com.github.ifrankwang.spring.api.facade.AccessControlFacade;
import com.github.ifrankwang.spring.exception.ServiceException;
import com.github.ifrankwang.spring.module.security.entity.*;
import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.service.*;
import com.github.ifrankwang.spring.module.system.service.SystemConfigService;
import com.github.ifrankwang.spring.util.ApplicationContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.github.ifrankwang.spring.module.security.enums.AccessLevel.PRIVATE;
import static org.hibernate.internal.util.collections.CollectionHelper.isEmpty;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

/**
 * @author Frank Wang
 */
@Service
public class AccessControlFacadeImpl implements AccessControlFacade {
    private final ApiService apiService;
    private final UserService userService;
    private final RoleService roleService;
    private final RoleAuthorityService roleAuthorityService;
    private final SystemConfigService systemConfigService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public AccessControlFacadeImpl(ApiService apiService, UserService userService, RoleService roleService, RoleAuthorityService roleAuthorityService, SystemConfigService systemConfigService) {
        this.apiService = apiService;
        this.userService = userService;
        this.roleService = roleService;
        this.roleAuthorityService = roleAuthorityService;
        this.systemConfigService = systemConfigService;
    }

    @Override
    public void canAccess() throws InsufficientPermissionException {
        canAccess(null, null);
    }

    @Override
    public void canAccess(@Nullable Long businessId, @Nullable Class<? extends BusinessGetter> getterClass) throws InsufficientPermissionException {
        boolean canAccess;
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        final HttpServletRequest request = requestAttributes.getRequest();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            canAccess = accessControl(authentication, request, businessId, getterClass);
        } catch (ServiceException e) {
            logger.warn("\n鉴权失败！详细信息：{}", e.getMessage());
            canAccess = false;
        }

        if (!canAccess) {
            throw new InsufficientPermissionException();
        }
    }

    private boolean accessControl(Authentication authentication, HttpServletRequest request, @Nullable Long businessId, @Nullable Class<? extends BusinessGetter> getterClass) throws ServiceException {
        final String idPlaceHolder = "{id}";
        final String uriPath = request.getRequestURI().replace(contextPath, "");
        final String requestPath = Optional.ofNullable(businessId)
                                           .map(id -> uriPath.replace(id.toString(), idPlaceHolder))
                                           .orElse(uriPath);
        final ApiEntity api = apiService.findByMethodAndPath(ApiMethod.valueOf(request.getMethod()), requestPath);
        final UserEntity user = userService.findByEmail((String) authentication.getPrincipal());

        if (null == businessId || null == getterClass) {
            return genericAccessControl(api, user);
        } else {
            final BusinessGetter getter = ApplicationContextHelper.getBean(getterClass);
            final Business business = getter.findById(businessId);
            return businessAccessControl(business, api, user);
        }
    }

    private boolean genericAccessControl(ApiEntity api, UserEntity user) {
        return genericAccessControl(null, api, user);
    }

    private boolean genericAccessControl(@Nullable Business business, ApiEntity api, UserEntity user) {
        if (systemConfigService.getSuperAdmin().equals(user)) {
            return true;
        }

        final List<RoleAuthorityEntity> roleAuthorities = getGenericRoleAuthorities(api, user);

        if (null != business) {
            // 一般是PUT/DELETE
            return hasRightAccessLevel(user, roleAuthorities, business);
        }
        // 一般是GET/POST的操作
        return isNotEmpty(roleAuthorities);
    }

    private boolean businessAccessControl(Business business, ApiEntity api, UserEntity user) {
        // 是否有非业务的操作权限
        final boolean hasGenericAccessPrivilege = genericAccessControl(business, api, user);
        if (hasGenericAccessPrivilege) {
            return true;
        }

        // 是否有业务的操作权限
        final List<RoleAuthorityEntity> roleAuthorities = getBusinessRoleAuthorities(business, api, user);
        return hasRightAccessLevel(user, roleAuthorities, business);
    }

    private List<RoleAuthorityEntity> getGenericRoleAuthorities(ApiEntity api, UserEntity user) {
        final AuthorityEntity requiredAuthority = api.getAuthority();
        final List<RoleEntity> userRoles = roleService.findGenericRoleOfUser(user);
        return roleAuthorityService.findByRolesAndAuthority(userRoles, requiredAuthority);
    }

    private List<RoleAuthorityEntity> getBusinessRoleAuthorities(Business business, ApiEntity api, UserEntity user) {
        final AuthorityEntity requiredAuthority = api.getAuthority();
        final GroupEntity requiredGroup = business.getGroup();
        final List<RoleEntity> userRoles;
        if (null != requiredGroup) {
            userRoles = roleService.findBusinessRoleOfUser(user, requiredGroup);
        } else {
            userRoles = roleService.findGenericRoleOfUser(user);
        }
        return roleAuthorityService.findByRolesAndAuthority(userRoles, requiredAuthority);
    }

    private boolean hasRightAccessLevel(UserEntity user, List<RoleAuthorityEntity> roleAuthorities, Business business) {
        if (isEmpty(roleAuthorities)) {
            return false;
        }
        // 资源拥有者需要起码PRIVATE的权限，否则要大于PRIVATE的权限
        final boolean isOwner = user.equals(business.getCreator());
        return roleAuthorities.stream().anyMatch(roleAuthority -> {
            return isOwner ? PRIVATE.compareTo(roleAuthority.getAccessLevel()) <= 0 :
                   PRIVATE.compareTo(roleAuthority.getAccessLevel()) < 0;
        });
    }
}
