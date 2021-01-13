package com.gjsyoung.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * create by cairuojin on 2019/01/08
 */
@Component
public class Boss {

    @Autowired(required = false)
    private Car car;

    @Autowired
    public Boss(Car car){
        this.car = car;
    }

    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }


    private String name;
    private int age;

    public Boss(){

    }
    public Boss(Car car, String name, int age) {
        this.car = car;
        this.name = name;
        this.age = age;
    }



    public void drive(){
        System.out.println("boss drive");
    }

    public Car getCar() {
        return car;
    }

}
