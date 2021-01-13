package 第一章_线程API.线程优先级;

/**
 * @author cairuojin
 * @create 2018-12-06 21:42
 */
class myThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("线程1：" + currentThread().getPriority());
        myThread2 t = new myThread2();
        t.start();
    }
}
class myThread2 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("线程2：" + currentThread().getPriority());
    }
}
public class Main {
    public static void main(String[] args) {
        myThread m1 = new myThread();
        m1.setPriority(10);
        m1.start();
    }
}