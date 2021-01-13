package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 * 其他API ：
 *      getHoldCount() 查询当前线程保持此锁的个数
 *      getQueueLength() 返回正等待获取此锁定的线程估计数(正在等待该锁释放的线程数)
 */

class MyService6{
    public ReentrantLock lock = new ReentrantLock();
    public void serviceMethod1(){
        try {
            lock.lock();
            System.out.println(" 进入方法 : " + Thread.currentThread().getName());
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class _7Main {
    public static void main(String[] args)  {
        MyService6 myService6 = new MyService6();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                myService6.serviceMethod1();
            }
        };
        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10; i ++){
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
        try {
            Thread.sleep(11100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myService6.lock.getQueueLength());
    }
}
