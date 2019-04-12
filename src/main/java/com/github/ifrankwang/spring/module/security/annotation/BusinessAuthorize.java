package com.github.ifrankwang.spring.module.security.annotation;

import com.github.ifrankwang.spring.module.security.service.BusinessGetter;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Frank Wang
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface BusinessAuthorize {
    String id();

    Class<? extends BusinessGetter> getterClass();
}
