package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 * 使用ReentrantLock锁
 */

class MyService{
    private Lock lock = new ReentrantLock();
    public void testMethod(){
        lock.lock();                //此处锁lock对象
        for(int i = 0; i < 5; i ++){
            System.out.println(Thread.currentThread().getName() + "  " + ( i + 1 ));
        }
        lock.unlock();              //解锁lock对象
    }
}
class MyThread1 extends Thread{
    private MyService myService;
    public MyThread1(MyService myService) {
        this.myService = myService;
    }
    @Override
    public void run() {
        myService.testMethod();
    }
}


public class _1Main {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread1 a1 = new MyThread1(myService);
        MyThread1 a2 = new MyThread1(myService);
        MyThread1 a3 = new MyThread1(myService);
        MyThread1 a4 = new MyThread1(myService);
        MyThread1 a5 = new MyThread1(myService);
        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a5.start();
    }
}
