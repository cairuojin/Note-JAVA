package 第四章_Lock的使用._2使用ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * create by cairuojin on 2019/01/07
 * 写写互斥
 */
class Service1{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void write(){
        try {
            lock.writeLock().lock();
            System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}

class MyThread3 extends Thread{
    private Service1 service;
    public MyThread3(Service1 service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.write();
    }
}
class MyThread4 extends Thread{
    private Service1 service;
    public MyThread4(Service1 service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.write();
    }
}

public class _2Main {

    public static void main(String[] args) {
        Service1 service = new Service1();
        MyThread3 myThread3 = new MyThread3(service);
        MyThread4 myThread4 = new MyThread4(service);
        myThread3.start();
        myThread4.start();
    }
}
