package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 * 测试2
 */

class MyService1{
    private Lock lock = new ReentrantLock();
    public void methodA(){
        try{
            lock.lock();
            System.out.println("A begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("A end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void methodB(){
        try{
            lock.lock();
            System.out.println("B begin " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("B end " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

//两个线程调用一个方法   两个方法共4个线程
class MyThread2 extends Thread{
    private MyService1 myService1;
    public MyThread2(MyService1 myService1) {
        this.myService1 = myService1;
    }
    @Override
    public void run() {
        super.run();
        myService1.methodA();
    }
}
class MyThread2A extends Thread{
    private MyService1 myService1;
    public MyThread2A(MyService1 myService1) {
        this.myService1 = myService1;
    }
    @Override
    public void run() {
        super.run();
        myService1.methodA();
    }
}

class MyThread3 extends Thread{
    private MyService1 myService1;
    public MyThread3(MyService1 myService1) {
        this.myService1 = myService1;
    }
    @Override
    public void run() {
        super.run();
        myService1.methodB();
    }
}
class MyThread3A extends Thread{
    private MyService1 myService1;
    public MyThread3A(MyService1 myService1) {
        this.myService1 = myService1;
    }
    @Override
    public void run() {
        super.run();
        myService1.methodB();
    }
}


public class _2Main {

    public static void main(String[] args) {
        MyService1 myService1 = new MyService1();
        MyThread2 myThread2 = new MyThread2(myService1);
        MyThread2A myThread2A = new MyThread2A(myService1);
        MyThread3 myThread3 = new MyThread3(myService1);
        MyThread3A myThread3A = new MyThread3A(myService1);
        myThread2.start();
        myThread2A.start();
        myThread3.start();
        myThread3A.start(); //效果跟synchronized一样
    }
}
