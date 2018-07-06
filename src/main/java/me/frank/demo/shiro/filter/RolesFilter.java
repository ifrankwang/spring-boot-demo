package me.frank.demo.shiro.filter;

import me.frank.demo.util.ServletUtil;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static me.frank.demo.exception.ServiceException.INSUFFICIENT_PERMISSION;
import static org.apache.shiro.web.util.WebUtils.toHttp;

/**
 * @author 王明哲
 */
public class RolesFilter extends RolesAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        ServletUtil.goError(toHttp(request), toHttp(response), INSUFFICIENT_PERMISSION);
        return false;
    }
}
