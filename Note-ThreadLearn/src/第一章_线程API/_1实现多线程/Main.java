package 第一章_线程API._1实现多线程;

/**
 * @author cairuojin
 * @create 2018-11-25 22:09
 */

class Mythread1 extends Thread {
    private int ticket = 5;
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName()+ "卖票：ticket = " + ticket--);
            }
        }
    }
}


class Mythread2 implements Runnable {
    private int ticket = 5;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName()+ "卖票：ticket = " + ticket--);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Mythread2 mythread1 = new Mythread2();
        Thread thread1 = new Thread(mythread1,"A");
        Thread thread2 = new Thread(mythread1,"B");
        Thread thread3 = new Thread(mythread1,"C");
        thread1.start();
        thread2.start();
        thread3.start();
//        System.out.println("主线程..");
//        Mythread1 mythread1 = new Mythread1();
//        new Thread(mythread1).start();
//        new Thread(mythread1).start();
//        new Thread(mythread1).start();
    }
}