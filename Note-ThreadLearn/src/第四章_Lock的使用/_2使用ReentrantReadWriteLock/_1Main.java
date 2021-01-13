package 第四章_Lock的使用._2使用ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * create by cairuojin on 2019/01/07
 * 读读共享
 */

class Service{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(){
        try {
            lock.readLock().lock();
            System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

class MyThread1 extends Thread{
    private Service service;
    public MyThread1(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.read();
    }
}
class MyThread2 extends Thread{
    private Service service;
    public MyThread2(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.read();
    }
}

public class _1Main {

    public static void main(String[] args) {
        Service service = new Service();
        MyThread1 myThread1 = new MyThread1(service);
        MyThread2 myThread2 = new MyThread2(service);
        myThread1.start();
        myThread2.start();
    }
}
