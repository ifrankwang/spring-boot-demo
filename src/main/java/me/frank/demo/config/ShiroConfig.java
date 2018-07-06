package me.frank.demo.config;

import me.frank.demo.properties.JwtProperties;
import me.frank.demo.service.JwtService;
import me.frank.demo.service.UserService;
import me.frank.demo.shiro.filter.FilterChainDefinition;
import me.frank.demo.shiro.filter.RolesFilter;
import me.frank.demo.shiro.filter.TokenFilter;
import me.frank.demo.shiro.realm.TokenRealm;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王明哲
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class ShiroConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Realm realm(UserService userService, JwtService jwtService) {
        return new TokenRealm(userService, jwtService);
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);

        return manager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager,
                                                         ShiroFilterChainDefinition shiroFilterChainDefinition) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new TokenFilter());
        filterMap.put("roles", new RolesFilter());

        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);

        factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition.getFilterChainMap());
        return factoryBean;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        final FilterChainDefinition chainDefinition = new FilterChainDefinition();
        final String[] nonAuthPath = {"/api**/no-auth/**", "/resources/**", "/static/**", "/public/**",
                "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs/**", "/webjars/**", "/*.html",
                "/api/register", "/api/login"};

        chainDefinition.addPathDefinition("/api/admin/**", "jwt, roles[admin]");
        chainDefinition.addPathDefinition("/api/user/**", "jwt, roles[user]");
        chainDefinition.addPathDefinition(nonAuthPath, "anon");
        chainDefinition.addPathDefinition("/**", "jwt");

        return chainDefinition;
    }

    @Bean
    protected CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }
}
