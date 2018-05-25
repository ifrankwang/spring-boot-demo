package me.frank.demo.service.impl;

import io.jsonwebtoken.Jwts;
import me.frank.demo.properties.JwtProperties;
import me.frank.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.lang.System.currentTimeMillis;
import static me.frank.demo.exception.ServiceException.INVALID_TOKEN;
import static me.frank.demo.properties.SecurityConst.TOKEN_PREFIX;

/**
 * @author 王明哲
 */
@Service
public class JwtServiceImpl implements JwtService {
    private final JwtProperties properties;

    @Autowired
    public JwtServiceImpl(JwtProperties properties) {
        this.properties = properties;
    }

    @Override
    public String genTokenFor(String subject) {
        final long validDuration = properties.getValidDuration();
        return TOKEN_PREFIX + Jwts.builder()
                                  .setSubject(subject)
                                  .setExpiration(new Date(currentTimeMillis() + validDuration))
                                  .signWith(HS256, properties.getEncryptKey())
                                  .compact();
    }

    @Override
    public String getSubjectFrom(String token) {
        try {
            return Jwts.parser()
                       .setSigningKey(properties.getEncryptKey())
                       .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                       .getBody()
                       .getSubject();
        } catch (Exception e) {
            throw INVALID_TOKEN;
        }
    }
}
