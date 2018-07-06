package me.frank.demo.shiro.filter;

import me.frank.demo.shiro.token.JwtToken;
import me.frank.demo.util.ServletUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import static me.frank.demo.exception.ServiceException.INSUFFICIENT_PERMISSION;
import static me.frank.demo.shiro.token.JwtToken.getTokenFrom;
import static org.apache.shiro.web.util.WebUtils.toHttp;

/**
 * @author 王明哲
 */
public class TokenFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            if (isLoginAttempt(request, response)) {
                return executeLogin(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        final JwtToken token = getTokenFrom((HttpServletRequest) request);
        return token.isValid();
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        final JwtToken token = getTokenFrom((HttpServletRequest) request);
        getSubject(request, response).login(token);
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        ServletUtil.goError(toHttp(request), toHttp(response), INSUFFICIENT_PERMISSION);
        return false;
    }
}
