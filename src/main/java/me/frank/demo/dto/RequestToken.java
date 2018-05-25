package me.frank.demo.dto;

import lombok.Data;

import static me.frank.demo.properties.SecurityConst.TOKEN_PREFIX;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 请求URI类
 *
 * @author 王明哲
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
        return isNotBlank(tokenValue) && tokenValue.startsWith(TOKEN_PREFIX);
    }

    public boolean isNotValidToken() {
        return !isValidToken();
    }
}
