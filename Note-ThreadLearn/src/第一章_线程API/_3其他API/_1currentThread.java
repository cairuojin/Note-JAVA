package _3其他API;

/**
 * create by cairuojin on 2018/11/28
 */

class myThread extends Thread{
    public myThread() {
        System.out.println("构造方法开始");
        System.out.println("currentThread.getName  " + Thread.currentThread().getName());   //当前线程
        System.out.println(Thread.currentThread().isAlive());
        System.out.println("this.getName  " + this.getName());  //this指的是myThread生成的对象
        System.out.println(this.isAlive());
        System.out.println("构造方法结束");
    }
    @Override
    public void run() {
        super.run();
        System.out.println("run方法开始");
        System.out.println("currentThread.getName  " + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().isAlive());
        System.out.println("this.getName  " + this.getName());
        System.out.println(this.isAlive());
        System.out.println("run方法结束");
    }
}
public class _1currentThread {
    public static void main(String[] args) {
        myThread tt = new myThread();
        Thread thread = new Thread(tt);
        tt.setName("ABC");
        thread.start();
    }
}
