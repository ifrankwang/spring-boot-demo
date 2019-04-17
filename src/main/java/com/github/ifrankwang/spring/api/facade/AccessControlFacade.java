package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.service.BusinessGetter;

import javax.annotation.Nullable;

/**
 * @author Frank Wang
 */
public interface AccessControlFacade {
    void canAccess() throws InsufficientPermissionException;

    void canAccess(@Nullable Long businessId, @Nullable Class<? extends BusinessGetter> getterClass) throws InsufficientPermissionException;
}
