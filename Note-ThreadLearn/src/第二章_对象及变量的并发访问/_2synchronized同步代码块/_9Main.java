package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * create by cairuojin on 2018/12/10
 * 注意锁对象的改变
 */
class MyService{
    private String lock = "123";
    public void testMethod(){
        try{
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + " begin" + System.currentTimeMillis());
                lock = "456";       //锁对象改变，导致后加入的线程抢夺的不是同个锁
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " end" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyThread1 extends Thread{
    private MyService myService;
    public MyThread1(MyService myService) {
        this.myService = myService;
    }
    @Override
    public void run() {
        super.run();
        myService.testMethod();
    }
}
class MyThread2 extends Thread{
    private MyService myService;
    public MyThread2(MyService myService) {
        this.myService = myService;
    }
    @Override
    public void run() {
        super.run();
        myService.testMethod();
    }
}

public class _9Main {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread1 m1 = new MyThread1(myService);
        MyThread2 m2 = new MyThread2(myService);
        m1.start();
//        try {
//            Thread.sleep(50);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        m2.start();
    }
}
