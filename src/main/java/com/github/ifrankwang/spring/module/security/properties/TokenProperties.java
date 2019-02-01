package com.github.ifrankwang.spring.module.security.properties;

import lombok.Data;

/**
 * JWT配置类
 *
 * @author Frank Wang
 */
@Data
public class TokenProperties {
    private long validDuration;
    private String encryptKey;
}
