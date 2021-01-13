package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 * 多个Condition类实现通知部分
 */

class MyService3{
    private Lock lock = new ReentrantLock();
    public Condition conditionA = lock.newCondition();      //有两个condition对象
    public Condition conditionB = lock.newCondition();
    public void awaitA(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "await A " + System.currentTimeMillis());
            conditionA.await();
            System.out.println(Thread.currentThread().getName() + " end await A " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void awaitB(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "await B " + System.currentTimeMillis());
            conditionB.await();
            System.out.println(Thread.currentThread().getName() + " end await B " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_A(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "signalAll A " + System.currentTimeMillis());
            conditionA.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void signalAll_B(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "signalAll B " + System.currentTimeMillis());
            conditionB.signalAll();
        } finally {
            lock.unlock();
        }
    }
}


class MyThread5 extends Thread{
    private MyService3 myService3;

    public MyThread5(MyService3 myService3) {
        this.myService3 = myService3;
    }

    @Override
    public void run() {
        myService3.awaitA();
    }
}
class MyThread6 extends Thread{
    private MyService3 myService3;

    public MyThread6(MyService3 myService3) {
        this.myService3 = myService3;
    }

    @Override
    public void run() {
        myService3.awaitB();
    }
}


public class _4Main {
    public static void main(String[] args) {
        MyService3 myService3 = new MyService3();
        MyThread5 myThread5 = new MyThread5(myService3);
        myThread5.start();
        MyThread6 myThread6 = new MyThread6(myService3);
        myThread6.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myService3.signalAll_A();   //只唤醒A
    }
}
