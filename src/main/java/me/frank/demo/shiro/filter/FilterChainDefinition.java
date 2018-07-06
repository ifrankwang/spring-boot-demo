package me.frank.demo.shiro.filter;

import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王明哲
 */
public class FilterChainDefinition implements ShiroFilterChainDefinition {
    private Map<String, String> filterChainMap = new LinkedHashMap<>();

    @Override
    public Map<String, String> getFilterChainMap() {
        return filterChainMap;
    }

    public void addPathDefinition(String antPath, String definition) {
        filterChainMap.put(antPath, definition);
    }

    public void addPathDefinition(String[] antPaths, String definition) {
        for (String antPath : antPaths) {
            filterChainMap.put(antPath, definition);
        }
    }

    public void addPathDefinition(List<Map<String, String>> pathDefinitions) {
        for (Map<String, String> antPath : pathDefinitions) {
            filterChainMap.putAll(antPath);
        }
    }

}
