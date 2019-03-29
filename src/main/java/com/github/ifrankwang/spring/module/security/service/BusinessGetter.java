package com.github.ifrankwang.spring.module.security.service;

import com.github.ifrankwang.spring.module.security.entity.Business;

/**
 * @author Frank Wang
 */
public interface BusinessGetter {
    Business findById(Long id);
}
