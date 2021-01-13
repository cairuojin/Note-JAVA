package _3其他API;

/**
 * create by cairuojin on 2018/11/28
 */

class myThread1 extends Thread{
    @Override
    public void run() {
        super.run();
        try {
            System.out.println(this.currentThread().getName() + "begin");
            Thread.sleep(2000);
            System.out.println(this.currentThread().getName() + "end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _2sleep {
    public static void main(String[] args) {
        myThread1 tt = new myThread1();
        System.out.println(System.currentTimeMillis());
        tt.run();
        System.out.println(System.currentTimeMillis());
    }
}
