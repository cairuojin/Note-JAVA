package 第三章_线程间通信._1等待通知机制;

/**
 * create by cairuojin on 2018/12/27
 * wait时被interrupted
 */

class Service{
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

class MyThread7 extends Thread{
    private Object lock;
    public MyThread7(Object lock) {
        this.lock = lock;
    }   //线程调用上面的wait方法
    @Override
    public void run() {
        Service service = new Service();
        service.waitMethod(lock);
    }
}

public class _5Main {

    public static void main(String[] args) {
        Object lock = new Object();
        MyThread7 tt = new MyThread7(lock);
        tt.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tt.interrupt();         //interrupt 会抛出异常并结束线程
    }


}
