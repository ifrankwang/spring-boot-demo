package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.exception.ServiceException;
import com.github.ifrankwang.spring.module.security.entity.*;
import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.service.*;
import com.github.ifrankwang.spring.util.ApplicateContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static com.github.ifrankwang.spring.module.security.enums.AccessLevel.PRIVATE;
import static org.hibernate.internal.util.collections.CollectionHelper.isEmpty;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

/**
 * @author Frank Wang
 */
@Service
public class AccessControlFacade {
    private final ApiService apiService;
    private final UserService userService;
    private final RoleService roleService;
    private final RoleAuthorityService roleAuthorityService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AccessControlFacade(ApiService apiService, UserService userService, RoleService roleService, RoleAuthorityService roleAuthorityService) {
        this.apiService = apiService;
        this.userService = userService;
        this.roleService = roleService;
        this.roleAuthorityService = roleAuthorityService;
    }

    public void canAccess() throws InsufficientPermissionException {
        canAccess(null, null);
    }

    public void canAccess(@Nullable Long businessId, @Nullable Class<? extends BusinessGetter> getterClass) throws InsufficientPermissionException {
        boolean canAccess;
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String uriPath = Optional.ofNullable(ServletUriComponentsBuilder.fromCurrentRequestUri()
                                                                              .build().getPath())
                                       .map(path -> path.replace(contextPath, ""))
                                       .orElse(null);

        try {
            canAccess = accessControl(authentication, uriPath, businessId, getterClass);
        } catch (ServiceException e) {
            logger.warn("\n鉴权失败！详细信息：{}", e.getMessage());
            canAccess = false;
        }

        if (!canAccess) {
            throw new InsufficientPermissionException();
        }
    }

    private boolean accessControl(Authentication authentication, String apiPath, @Nullable Long businessId, @Nullable Class<? extends BusinessGetter> getterClass) throws ServiceException {
        // TODO 光用path不够，还需加上requestMethod，以及如何把实际的id转成{id}形式的问题
        final ApiEntity api = apiService.findByPath(apiPath);
        final UserEntity user = userService.findByEmail((String) authentication.getPrincipal());

        if (null == businessId) {
            return genericAccessControl(api, user);
        } else {
            final BusinessGetter getter = ApplicateContextHelper.getBean(getterClass);
            final Business business = getter.findById(businessId);
            return businessAccessControl(business, api, user);
        }
    }

    private boolean genericAccessControl(ApiEntity api, UserEntity user) {
        return genericAccessControl(null, api, user);
    }

    private boolean genericAccessControl(@Nullable Business business, ApiEntity api, UserEntity user) {
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
        final List<RoleEntity> userRoles = roleService.findBusinessRoleOfUser(user, requiredGroup);
        return roleAuthorityService.findByRolesAndAuthority(userRoles, requiredAuthority);
    }

    private boolean hasRightAccessLevel(UserEntity user, List<RoleAuthorityEntity> roleAuthorities, Business business) {
        if (isEmpty(roleAuthorities)) {
            return false;
        }
        // 资源拥有者需要起码PRIVATE的权限，否则要大于PRIVATE的权限
        final boolean isOwner = user.equals(business.getCreator());
        return roleAuthorities.stream().anyMatch(roleAuthority -> {
            return isOwner ? PRIVATE.compareTo(roleAuthority.getAccessLevel()) >= 0 :
                   PRIVATE.compareTo(roleAuthority.getAccessLevel()) > 0;
        });
    }
}
