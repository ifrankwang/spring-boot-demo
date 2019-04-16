package com.github.ifrankwang.spring.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Frank Wang
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> type) {
        return context.getBean(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
