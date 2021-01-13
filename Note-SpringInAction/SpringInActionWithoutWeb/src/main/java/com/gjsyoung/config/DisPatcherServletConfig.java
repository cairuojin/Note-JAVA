package com.gjsyoung.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * @author cairuojin
 * @create 2019-01-13 22:06
 */
public class DisPatcherServletConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }                       //配置拦截路径

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }     //加载非Web配置

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class};
    }   //加载其他Web配置

    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setLoadOnStartup(1);                       //设置优先级
        registration.setInitParameter("abc","1");       //初始化参数
        registration.setMultipartConfig(new MultipartConfigElement("F:/test/spittr/uploads")); //启用multipart请求
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
          new MyFilter()
        };
    }
}