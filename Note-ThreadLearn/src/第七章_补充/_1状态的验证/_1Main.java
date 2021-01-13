package 第七章_补充._1状态的验证;

/**
 * @author cairuojin
 * @create 2019-01-07 22:28
 * 验证NEW RUNNABLE TERMINATED
 */

class MyThread extends Thread{
    public MyThread(){
        System.out.println("构造函数中的状态" + Thread.currentThread().getState());
    }
    @Override
    public void run() {
        super.run();
        System.out.println("run函数中的状态" + Thread.currentThread().getState());
    }
}


class ThreadB extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println("run函数中的状态" + Thread.currentThread().getState());
    }
}


class ThreadA extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("run函数中的状态" + Thread.currentThread().getState());
    }
}
public class _1Main {
    public static void main(String[] args) {
        try {
            MyThread tt = new MyThread();
            System.out.println("main方法中未启动状态" + tt.getState());
            Thread.sleep(1000);
            tt.start();
            Thread.sleep(1000);
            System.out.println("main方法中启动后状态" + tt.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadGroup group = new ThreadGroup("我的线程组");
        Thread athread = new Thread(group,threadA);
        Thread bthread = new Thread(group,threadB);
        athread.start();
        bthread.start();
    }
}