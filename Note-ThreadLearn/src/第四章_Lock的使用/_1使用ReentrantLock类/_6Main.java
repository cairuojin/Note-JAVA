package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/03
 */

class MyService5 {
    private ReentrantLock lock;

    public MyService5(boolean isFair) {
        this.lock = new ReentrantLock(isFair);
    }

    public void serviceMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "获得锁");
        } finally {
            lock.unlock();
        }
    }
}

public class _6Main {
    public static void main(String[] args) {
        final MyService5 service5 = new MyService5(false);   //公平锁 / 非公平锁
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程：" + Thread.currentThread().getName() + "运行了");
                service5.serviceMethod();
            }
        };
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
            threads[i].start();
        }
    }
}
