package me.frank.demo.security;

import me.frank.demo.dto.RequestToken;
import me.frank.demo.entity.AppUser;
import me.frank.demo.service.JwtService;
import me.frank.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static me.frank.demo.dto.RequestToken.from;
import static me.frank.demo.exception.ServiceException.INVALID_TOKEN;
import static me.frank.demo.properties.SecurityConst.AUTH_FAILED_URL;
import static me.frank.demo.properties.SecurityConst.HEADER_NAME;

/**
 * Token校验过滤器
 *
 * @author 王明哲
 */
public class AuthorizationFilter extends BasicAuthenticationFilter {
    private final UserService userService;
    private final JwtService jwtService;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               UserService userService, JwtService jwtService) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {
        final String uri = request.getRequestURI();
        final RequestToken requestToken = from(request.getHeader(HEADER_NAME));

        // 没有token时不校验token
        if (requestToken.isNotValidToken() || uri.contains(AUTH_FAILED_URL)) {
            chain.doFilter(request, response);
            return;
        }

        final UsernamePasswordAuthenticationToken authToken = getAuthTokenFromRequestToken(requestToken);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthTokenFromRequestToken(RequestToken requestToken) {
        final String username = jwtService.getSubjectFrom(requestToken.getTokenValue());
        final AppUser user = userService.findByUsername(username).orElseThrow(() -> INVALID_TOKEN);
        final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
        authToken.setDetails(user);
        return authToken;
    }
}
