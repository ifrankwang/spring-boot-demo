package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.exception.ServiceException;
import com.github.ifrankwang.spring.module.security.entity.*;
import com.github.ifrankwang.spring.module.security.service.*;
import com.github.ifrankwang.spring.util.ApplicateContextHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.List;

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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AccessControlFacade(ApiService apiService, UserService userService, RoleService roleService, RoleAuthorityService roleAuthorityService) {
        this.apiService = apiService;
        this.userService = userService;
        this.roleService = roleService;
        this.roleAuthorityService = roleAuthorityService;
    }

    public boolean canAccess(Authentication authentication, String apiPath) {
        return canAccess(authentication, apiPath, null, null);
    }

    public boolean canAccess(Authentication authentication, String apiPath, @Nullable Long businessId, @Nullable Class<BusinessGetter> getterClass) {
        try {
            return accessControl(authentication, apiPath, businessId, getterClass);
        } catch (ServiceException e) {
            logger.warn("\n鉴权失败！", e);
        }
        return false;
    }

    private boolean accessControl(Authentication authentication, String apiPath, @Nullable Long businessId, @Nullable Class<BusinessGetter> getterClass) throws ServiceException {
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
