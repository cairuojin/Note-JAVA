package 第三章_线程间通信._3类ThreadLocal的使用;

/**
 * create by cairuojin on 2019/01/02
 * 验证ThreadLocal具有隔离性
 */

class Tools {
    public static ThreadLocal tl = new ThreadLocal();
}
class MyThread5 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Tools.tl.set("AAAA" + (i + 1));
                System.out.println("ThreadA get " + Tools.tl.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyThread6 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Tools.tl.set("BBBB" + (i + 1));
                System.out.println("ThreadB get " + Tools.tl.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class _1Main {
    public static void main(String[] args) {
        try {
            MyThread5 t1 = new MyThread5();
            MyThread6 t2 = new MyThread6();
            t1.start();
            t2.start();
            for (int i = 0; i < 100; i++) {
                Tools.tl.set("MMMM" + (i + 1));
                System.out.println("Main get " + Tools.tl.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
