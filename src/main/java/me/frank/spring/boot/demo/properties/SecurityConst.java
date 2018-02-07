package me.frank.spring.boot.demo.properties;

public class SecurityConst {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";
    public static final String API_PREFIX = "/api";
    public static final String LOGIN_URL = "/login";
    public static final String NO_AUTH_URL = API_PREFIX + "/no-auth";
    public static final String AUTH_FAILED_URL = API_PREFIX + "/auth-failed";

    public static final String ATTR_ERROR = "error";
    public static final String ATTR_USER = "user";
}
