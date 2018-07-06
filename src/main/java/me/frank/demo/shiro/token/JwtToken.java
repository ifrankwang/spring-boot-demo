package me.frank.demo.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;

import static me.frank.demo.properties.SecurityConst.HEADER_NAME;
import static me.frank.demo.properties.SecurityConst.TOKEN_PREFIX;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author 王明哲
 */
public class JwtToken implements AuthenticationToken {
    private String token;

    private JwtToken(String token) {
        this.token = token;
    }

    public static JwtToken getTokenFrom(HttpServletRequest request) {
        final String tokenString = request.getHeader(HEADER_NAME);
        return new JwtToken(tokenString);
    }

    public boolean isValid() {
        return isNotBlank(token) && token.startsWith(TOKEN_PREFIX);
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
