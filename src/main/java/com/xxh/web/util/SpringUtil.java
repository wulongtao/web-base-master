package com.xxh.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * 代码的形式使用框架中的一些方法
 * @author 小小黑
 */
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        applicationContext = arg0;
    }

    public static Object getBean(String id) {
        Object object = null;
        object = applicationContext.getBean(id);
        return object;
    }

    /**
     * 获取国际化的内容
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        if (Objects.isNull(key) || Objects.equals(key, "")) {
            return "";
        }
        return SpringUtil.getApplicationContext().getMessage(key, null, null);
    }

    public static String getWebappRootPath() {
        return System.getProperty("app.root");
    }
}
