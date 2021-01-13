package com.gjsyoung.test.AOPTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author cairuojin
 * @create 2019-01-12 11:38
 */

@Aspect
@Component
public class CarAopServiceImpl{

    @Pointcut("execution(* com.gjsyoung.test.AOPTest.Car.setName(..))")
    public void setCarName(){}

    @Pointcut("execution(* com.gjsyoung.test.AOPTest.Car.drive(..))")
    public void carDrive(){}

    @Around("setCarName()")       //环绕通知：调用setName前后记录日志
    public void logService(ProceedingJoinPoint jp){
        try {
            System.out.println("前置通知： 日志开始记录-car" + System.currentTimeMillis() );
            System.out.println("控制权移交");
            jp.proceed();
            System.out.println("后置通知： 日志在此记录-car" + System.currentTimeMillis());
        } catch (Throwable throwable) {
            System.out.println("异常通知： 错误！");
        }

    }

    @After("carDrive()")         //调用drive前
    public void startService() {
        System.out.println("发动机启动-----------");
    }

    @Before("carDrive()")         //调用drive后
    public void endService() {
        System.out.println("发动机熄灭------------");
    }

    @AfterThrowing("carDrive()")   //drive异常
    public void exceptionService() {
        System.out.println("发动机故障");
    }
}