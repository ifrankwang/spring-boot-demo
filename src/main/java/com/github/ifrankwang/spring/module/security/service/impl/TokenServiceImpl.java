package com.github.ifrankwang.spring.module.security.service.impl;

import com.github.ifrankwang.spring.module.security.exception.InsufficientPermissionException;
import com.github.ifrankwang.spring.module.security.exception.LoginInfoExpiredException;
import com.github.ifrankwang.spring.module.security.properties.SecurityConst;
import com.github.ifrankwang.spring.module.security.properties.TokenProperties;
import com.github.ifrankwang.spring.module.security.service.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.lang.System.currentTimeMillis;

/**
 * @author Frank Wang
 */
@Service
public class TokenServiceImpl implements TokenService {
    private final TokenProperties properties;

    @Autowired
    public TokenServiceImpl(TokenProperties properties) {
        this.properties = properties;
    }

    @Override
    public String genTokenFor(String subject) {
        final long validDuration = properties.getValidDuration();
        return SecurityConst.TOKEN_PREFIX + Jwts.builder()
                                                .setSubject(subject)
                                                .setExpiration(new Date(currentTimeMillis() + validDuration))
                                                .signWith(HS256, properties.getEncryptKey())
                                                .compact();
    }

    @Override
    public String getSubjectFrom(String token) throws LoginInfoExpiredException, InsufficientPermissionException {
        try {
            return Jwts.parser()
                       .setSigningKey(properties.getEncryptKey())
                       .parseClaimsJws(token.replace(SecurityConst.TOKEN_PREFIX, ""))
                       .getBody()
                       .getSubject();
        } catch (ExpiredJwtException e) {
            throw new LoginInfoExpiredException();
        } catch (Exception e) {
            throw new InsufficientPermissionException();
        }
    }
}
