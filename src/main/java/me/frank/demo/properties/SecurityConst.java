package me.frank.demo.properties;

/**
 * 安全常量配置类
 *
 * @author 王明哲
 */
public class SecurityConst {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    public static final String API_PREFIX = "/api";
    public static final String LOGIN_URL = "/login";
    public static final String REGISTER_URL = "/register";
    public static final String NO_AUTH_URL = "/no-auth";
    public static final String AUTH_FAILED_URL = API_PREFIX + NO_AUTH_URL + "/auth-failed";

    public static final String ATTR_ERROR = "error";
}
