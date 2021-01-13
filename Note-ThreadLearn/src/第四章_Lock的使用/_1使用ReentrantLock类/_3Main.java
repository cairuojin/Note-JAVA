package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 * Condition类
 */

class MyService2{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void await(){
        try{
            lock.lock();                    //必须先获得此锁
            System.out.println("wait - A");
            condition.await();              //此锁的此condition wait
            System.out.println("wait - B");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();                  //finally   unlock
        }
    }
    public void signal(){
        try{
            lock.lock();
            System.out.println("signal - " + System.currentTimeMillis());
            condition.signal();             //解锁此Contional
        } finally {
            lock.unlock();
        }
    }
}

class MyThread4 extends Thread{
    private MyService2 myService2;
    public MyThread4(MyService2 myService2) {
        this.myService2 = myService2;
    }
    @Override
    public void run() {
        super.run();
        myService2.await();
    }
}
public class _3Main {
    public static void main(String[] args) {
        MyService2 myService2 = new MyService2();
        MyThread4 myThread4 = new MyThread4(myService2);
        myThread4.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myService2.signal();
    }
}
