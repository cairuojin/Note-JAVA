package com.gjsyoung.aop;

/**
 * create by cairuojin on 2019/01/08
 */
public class TestAOP {

    public void beforeMethod(){
        System.out.println("调用方法前输出");
    }

    public void afterMethod(){
        System.out.println("调用方法后输出");
    }

}
