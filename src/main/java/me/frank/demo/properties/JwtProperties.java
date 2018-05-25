package me.frank.demo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JWT配置类
 *
 * @author 王明哲
 */
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private long validDuration;
    private String encryptKey;
}
