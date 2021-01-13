package 第三章_线程间通信._2方法join的使用;

/**
 * create by cairuojin on 2019/01/02
 */

class MyThread3 extends Thread{
    private MyThread4 thread4;

    public MyThread3(MyThread4 thread4) {
        this.thread4 = thread4;
    }

    @Override
    public void run() {
        super.run();
        try {
            synchronized (thread4){
                System.out.println("begin A ThreadName=" + Thread.currentThread().getName() + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end A ThreadName=" + Thread.currentThread().getName() + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread4 extends Thread{
    @Override
    synchronized public void run() {
        super.run();
        try {
            System.out.println("begin B ThreadName=" + Thread.currentThread().getName() + System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println("end B ThreadName=" + Thread.currentThread().getName() + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _3Main {

    public static void main(String[] args) {
        try {
            MyThread4 t4 = new MyThread4();
            MyThread3 t3 = new MyThread3(t4);
            t3.start();
            t4.start();
            t4.join();      //两个线程同时争抢B锁  如果t3线程比t4先抢到B锁，则main会比t4先执行
            System.out.println("main" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
