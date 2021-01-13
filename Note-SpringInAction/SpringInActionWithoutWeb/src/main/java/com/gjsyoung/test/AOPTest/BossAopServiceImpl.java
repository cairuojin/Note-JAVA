package com.gjsyoung.test.AOPTest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cairuojin
 * @create 2019-01-12 11:39
 */
@Aspect
@Component
public class BossAopServiceImpl{

    public Map<String , Integer> countMap = new HashMap<String, Integer>();

    @Pointcut("execution(* com.gjsyoung.test.AOPTest.Boss.drive(String)) && args(carName)")
    public void bossDriveCar(String carName){}

    @Before("bossDriveCar(carName)")
    public void countName(String carName) {
        System.out.println("老板开车：" + carName + " 时间" + System.currentTimeMillis());
        Integer count = countMap.get(carName);
        if(count == null)
            count = 0;
        countMap.put(carName, count + 1);
    }

}