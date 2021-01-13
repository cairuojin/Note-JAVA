package com.gjsyoung.test.AOPTest;

import org.springframework.stereotype.Component;

/**
 * create by cairuojin on 2019/01/08
 */
@Component
public class Car {

    private int price;  //价格
    private int seat;   //座位
    private String name;

    public void drive(){
        System.out.println("car drive");
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", seat=" + seat +
                ", name='" + name + '\'' +
                '}';
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
