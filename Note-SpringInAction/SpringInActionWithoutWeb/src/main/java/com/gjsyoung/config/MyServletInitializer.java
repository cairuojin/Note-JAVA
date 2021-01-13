package com.gjsyoung.config;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * create by cairuojin on 2019/01/16
 */
//public class MyServletInitializer implements WebApplicationInitializer {
public class MyServletInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        Dynamic myFilter = servletContext.addFilter("MyFilter", MyFilter.class);    //注册Filter
        myFilter.addMappingForUrlPatterns(null, false, "/custom/*");    //设置Filter的映射路径

        servletContext.addListener(MyListener.class);   //注册Listener
    }
}
