package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 */
class MyService4 {
    private Lock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    private boolean hasValue = false;   //标志此时需要set还是get

    public void set() {
        try {
            lock.lock();                 //加锁
            if (hasValue == true) {
                condition.await();       //等待
            }
            System.out.println("※");
            hasValue = true;
            condition.signal();          //唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();                  //解锁
        }
    }
    public void get(){
        try {
            lock.lock();
            if (hasValue == false){
                condition.await();
            }
            System.out.println("●");
            hasValue = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyThread7 extends Thread{
    private MyService4 myService4;
    public MyThread7(MyService4 myService4) {
        this.myService4 = myService4;
    }
    @Override
    public void run() {
        while (true)
            myService4.get();
    }
}

class MyThread8 extends Thread{
    private MyService4 myService4;
    public MyThread8(MyService4 myService4) {
        this.myService4 = myService4;
    }
    @Override
    public void run() {
        while (true)
            myService4.set();
    }
}
public class _5Main {
    public static void main(String[] args) {
        MyService4 myService4 = new MyService4();
        MyThread7 myThread7 = new MyThread7(myService4);
        myThread7.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread8 myThread8 = new MyThread8(myService4);
        myThread8.start();
    }
}
