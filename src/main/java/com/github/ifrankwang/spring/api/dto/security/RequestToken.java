package com.github.ifrankwang.spring.api.dto.security;

import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import lombok.Data;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 请求URI类
 *
 * @author Frank Wang
 */
@Data
public class RequestToken {
    private String tokenValue;

    private RequestToken(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public static RequestToken from(String headerValue) {
        return new RequestToken(headerValue);
    }

    public boolean isValidToken() {
        return isNotBlank(tokenValue) && tokenValue.startsWith(SecurityConst.TOKEN_PREFIX);
    }

    public boolean isNotValidToken() {
        return !isValidToken();
    }
}
