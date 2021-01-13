package 第三章_线程间通信._2方法join的使用;

/**
 * create by cairuojin on 2019/01/02
 * 主线程调用join等待子线程执行完
 */
class MyThread1 extends Thread{
    @Override
    public void run() {
        try {
            int secondValue = (int) (Math.random() * 10000);    //随机等待时间
            System.out.println(secondValue);
            Thread.sleep(secondValue);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _1Main {
    public static void main(String[] args) {
        try {
            MyThread1 tt = new MyThread1();
            tt.start();
            tt.join();   //使得当前调用的线程（main）等待tt线程执行完再继续
            System.out.println("继续执行Main");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
