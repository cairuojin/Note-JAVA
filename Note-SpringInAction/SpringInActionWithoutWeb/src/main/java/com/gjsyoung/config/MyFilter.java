package com.gjsyoung.config;

import javax.servlet.*;
import java.io.IOException;

/**
 * create by cairuojin on 2019/01/16
 */
public class MyFilter implements Filter{
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("创建Filter");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("经过Filter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public void destroy() {
        System.out.println("销毁Filter");
    }
}
