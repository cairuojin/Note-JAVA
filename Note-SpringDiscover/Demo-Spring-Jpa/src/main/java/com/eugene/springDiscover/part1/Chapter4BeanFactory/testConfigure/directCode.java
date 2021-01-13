package com.eugene.springDiscover.part1.Chapter4BeanFactory.testConfigure;

import com.eugene.springDiscover.part1.domain.DowJones.DowJonesNewsListener;
import com.eugene.springDiscover.part1.domain.DowJones.DowJonesNewsPersister;
import com.eugene.springDiscover.part1.domain.FXNewsProvider;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @Classname directCode
 * @Description TODO
 * @Date 2021/1/6 23:07
 * @Created by cairuojin
 */
public class directCode {

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
        //创建beanDefinition
        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class,true);
        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class,true);
        AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class,true);
        // 将bean定义注册到容器中
        registry.registerBeanDefinition("djNewsProvider", newsProvider);
        registry.registerBeanDefinition("djListener", newsListener);
        registry.registerBeanDefinition("djPersister", newsPersister);

        // 指定依赖关系
        // 1. 可以通过构造方法注入方式（顺序与构造函数相同）
        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0, newsListener);
        argValues.addIndexedArgumentValue(1, newsPersister);
        newsProvider.setConstructorArgumentValues(argValues);

        // 2. 或者通过setter方法注入方式（第一个参数需要跟对象中的属性名称相同）
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("ifxNewsListener",newsListener));
        propertyValues.addPropertyValue(new PropertyValue("ifxNewsPersister",newsPersister));
        newsProvider.setPropertyValues(propertyValues);
        // 绑定完成
        return (BeanFactory)registry;
    }
}
