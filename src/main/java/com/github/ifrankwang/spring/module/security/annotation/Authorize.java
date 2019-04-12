package com.github.ifrankwang.spring.module.security.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Frank Wang
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Authorize {
}
