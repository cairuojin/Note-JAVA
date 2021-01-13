package 第三章_线程间通信._1等待通知机制;

/**
 * create by cairuojin on 2018/12/27
 */

class Service1{
    public void waitMethod(Object lock){    //锁方法 wait
        try{
            synchronized (lock){
                System.out.println("开始wait");
                lock.wait();
                System.out.println("结束wait");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread8 extends Thread{
    private Object lock;
    public MyThread8(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        Service1 service = new Service1();
        service.waitMethod(lock);
    }
}
class MyThread9 extends Thread{
    private Object lock;
    public MyThread9(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        Service1 service = new Service1();
        service.waitMethod(lock);
    }
}
class MyThread10 extends Thread{
    private Object lock;
    public MyThread10(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        Service1 service = new Service1();
        service.waitMethod(lock);
    }
}
public class _6Main {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread8 t1 = new MyThread8(lock);
        t1.start();
        MyThread9 t2 = new MyThread9(lock);
        t2.start();
        MyThread10 t3 = new MyThread10(lock);
        t3.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){
            lock.notify();
            lock.notify();
            lock.notify();
            lock.notify();
        }
    }
}
