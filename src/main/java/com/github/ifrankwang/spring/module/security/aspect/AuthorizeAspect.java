package com.github.ifrankwang.spring.module.security.aspect;

import com.github.ifrankwang.spring.api.facade.AccessControlFacade;
import com.github.ifrankwang.spring.module.security.annotation.Authorize;
import com.github.ifrankwang.spring.module.security.annotation.BusinessAuthorize;
import com.github.ifrankwang.spring.util.SpelExpressionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Frank Wang
 */
@Aspect
@Component
public class AuthorizeAspect {
    private final AccessControlFacade accessControlFacade;

    public AuthorizeAspect(AccessControlFacade accessControlFacade) {
        this.accessControlFacade = accessControlFacade;
    }

    @Around("@annotation(authorizeAnnotation)")
    public Object authorize(ProceedingJoinPoint joinPoint, Authorize authorizeAnnotation) throws Throwable {
        accessControlFacade.canAccess();
        return joinPoint.proceed();
    }

    @Around("@annotation(authorizeAnnotation)")
    public Object authorize(ProceedingJoinPoint joinPoint, BusinessAuthorize authorizeAnnotation) throws Throwable {
        final Long id = SpelExpressionUtil.parseJoinPoint(joinPoint, authorizeAnnotation.id(), Long.class);
        accessControlFacade.canAccess(id, authorizeAnnotation.getterClass());
        return joinPoint.proceed();
    }

}
