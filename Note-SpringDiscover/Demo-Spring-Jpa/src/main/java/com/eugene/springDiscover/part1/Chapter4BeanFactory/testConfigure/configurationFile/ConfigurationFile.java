package com.eugene.springDiscover.part1.Chapter4BeanFactory.testConfigure.configurationFile;

import com.eugene.springDiscover.part1.domain.FXNewsProvider;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

/**
 * @Classname configurationFile
 * @Description TODO
 * @Date 2021/1/7 23:01
 * @Created by cairuojin
 */
public class ConfigurationFile {


    public static void main(String[] args) {
        //构建BeanFacory
        DefaultListableBeanFactory beanRegistry = new DefaultListableBeanFactory();
        //创建beanDefinition及绑定其关系
        BeanFactory container = (BeanFactory)bindViaCode(beanRegistry);

        //获取bean，此时才会去组装对象（lazy-load）
        FXNewsProvider newsProvider = (FXNewsProvider)container.getBean("djNewsProvider");
        //调用方法
        newsProvider.getAndPersistNews();
    }


    /**
     * 绑定构造关系
     * @param registry
     * @return
     */
    public static BeanFactory bindViaCode(BeanDefinitionRegistry registry)
    {
        BeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(registry);
        beanDefinitionReader.loadBeanDefinitions("/part1/newsProviderDefine.properties");
        // 绑定完成
        return (BeanFactory)registry;
    }



}
