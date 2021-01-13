package com.gjsyoung.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * create by cairuojin on 2019/01/14
 */
@Configuration
@EnableWebMvc           //开启注解驱动
@ComponentScan(basePackages = {"com.gjsyoung.controller","com.gjsyoung.service"})   //组件扫描
public class WebConfig extends WebMvcConfigurerAdapter{

    //配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);                  //解析为JstlView
        resolver.setExposeContextBeansAsAttributes(true);       //JSTL
        return resolver;
    }


//    @Bean
//    public TilesConfigurer tilesConfigurer(){
//        TilesConfigurer tiles = new TilesConfigurer();
//        tiles.setDefinitions(new String[]{
//                "/WEB-INF/layout/tiles.xml" //指定Tile定义的位置
//        });
//        tiles.setCheckRefresh(true);    //启用刷新功能
//        return tiles;
//    }
//    @Bean
//    public ViewResolver viewResolver(){
//        return new TilesViewResolver();
//    }


//    @Bean
//    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){  //将视图名称解析为Thymeleaf视图
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        return viewResolver;
//    }
//    @Bean
//    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){    //处理模板并渲染结果
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        return templateEngine;
//    }
//
//    @Bean
//    public TemplateResolver templateResolver(){                                         //加载Thymeleaf模板
//        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode("HTML5");
//        return templateResolver;
//    }


    @Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

    //配置静态资源的处理: DispatcherServlet对静态资源放行，转发到 默认的Servlet上
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
