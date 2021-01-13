package 第二章_对象及变量的并发访问._3Volatile关键字;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cairuojin
 * @create 2018-12-17 21:38
 * 原子类
 */

class Mythread2 extends Thread{
    private AtomicInteger count = new AtomicInteger(0);     //原子类
    private int temp = 0;
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 10000; i++){
            System.out.println("t:" + ++temp);
            System.out.println("c:" + count.incrementAndGet());
        }
    }
}

public class _2Main {
    public static void main(String[] args) {
        Mythread2 tt = new Mythread2();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
        new Thread(tt).start();
    }
}