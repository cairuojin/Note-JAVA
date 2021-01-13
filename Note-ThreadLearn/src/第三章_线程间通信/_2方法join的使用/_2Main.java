package 第三章_线程间通信._2方法join的使用;

/**
 * create by cairuojin on 2019/01/02
 * join的使用
 */

class MyThread2 extends Thread{
    @Override
    public void run() {
        try {
            System.out.println("beginTime Sleep " + System.currentTimeMillis());
            Thread.sleep(5500);
            System.out.println("beginTime Sleep " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _2Main {
    public static void main(String[] args) {
        try {
            MyThread2 tt = new MyThread2();
            tt.start();
            tt.join(2000);          //只等待两秒
            System.out.println("main run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
