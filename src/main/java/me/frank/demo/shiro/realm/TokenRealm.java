package me.frank.demo.shiro.realm;

import me.frank.demo.entity.AppUser;
import me.frank.demo.service.JwtService;
import me.frank.demo.service.UserService;
import me.frank.demo.shiro.token.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import static me.frank.demo.exception.ServiceException.INVALID_USER;

/**
 * @author 王明哲
 */
public class TokenRealm extends AuthorizingRealm {
    private final UserService userService;
    private final JwtService jwtService;

    public TokenRealm(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        final String token = (String) getAvailablePrincipal(principals);
        final String username = jwtService.getSubjectFrom(token);
        final AppUser user = userService.findByUsername(username).orElseThrow(() -> INVALID_USER);
        final SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        authInfo.addRoles(user.getRoleNames());
        authInfo.addStringPermissions(user.getStringPermission());
        return authInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        final String username = jwtService.getSubjectFrom((String) token.getPrincipal());
        userService.findByUsername(username).orElseThrow(AuthenticationException::new);
        return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), getName());
    }
}
