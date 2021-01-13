package 第三章_线程间通信._1等待通知机制;

/**
 * @author cairuojin
 * @create 2018-12-17 23:13
 * notify方法
 */
class MyThread3 extends Thread {
    private Object lock;
    public MyThread3(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        super.run();
        try {
            synchronized (lock) {
                System.out.println("开始wait" + System.currentTimeMillis());
                lock.wait();
                System.out.println("停止wait" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyThread4 extends Thread{
    private Object lock;
    public MyThread4(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        super.run();
        synchronized (lock){
            System.out.println("开始notify" + System.currentTimeMillis());
            lock.notify();
            System.out.println("停止notify" + System.currentTimeMillis());
        }
    }
}

public class _3Main {
    public static void main(String[] args) {
        Object lock = new Object();
        MyThread3 t1 = new MyThread3(lock);
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MyThread4 t2 = new MyThread4(lock);
        t2.start();
    }
}