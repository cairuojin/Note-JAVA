package com.gjsyoung.service;

import com.gjsyoung.test.Boss;
import com.gjsyoung.test.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author cairuojin
 * @create 2019-01-08 23:00
 */
@Configuration
public class TestConfig {

    @Bean
    @Profile("dev")
    public Car carA(){
        Car car = new Car();
        car.setName("奔驰");
        return car;
    }
    @Bean
    @Profile("prod")
    public Car carB(){
        Car car = new Car();
        car.setName("宝马");
        return car;
    }

    @Bean
    public Boss boss(Car car){
        return new Boss(car);
    }
}