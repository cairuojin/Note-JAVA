package com.gjsyoung.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * create by cairuojin on 2019/01/16
 */
public class MyListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("创建Listener --- 完成业务");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("销毁Listener");
    }
}
