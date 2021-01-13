package 第四章_Lock的使用._2使用ReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * create by cairuojin on 2019/01/07
 * 读写互斥
 */
class Service2{
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    public void read(){
        try{
            lock.readLock().lock();
            System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write(){
        try{
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

class MyThread5 extends Thread{
    private Service2 service;
    public MyThread5(Service2 service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.write();
    }
}
class MyThread6 extends Thread{
    private Service2 service;
    public MyThread6(Service2 service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.read();
    }
}

public class _3Main {
    public static void main(String[] args) {
        Service2 service = new Service2();
        MyThread5 myThread5 = new MyThread5(service);
        MyThread6 myThread6 = new MyThread6(service);
        myThread5.start();
        myThread6.start();
    }
}

