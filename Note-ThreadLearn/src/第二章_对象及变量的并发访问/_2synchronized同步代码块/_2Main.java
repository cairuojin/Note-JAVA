package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * @author cairuojin
 * @create 2018-12-09 14:29
 * this锁和同步方法锁是同一个
 */
class Task1{
    synchronized public void otherMethod(){
        System.out.println("others " + Thread.currentThread().getName());
    }
    public void doTask(){
        System.out.println("doTask");
        synchronized (this){
            for(int i = 0; i < 10000; i++){
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}

class myThread3 extends Thread{
    private Task1 task1;

    public myThread3(Task1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        super.run();
        task1.doTask();
    }
}
class myThread4 extends Thread{
    private Task1 task1;

    public myThread4(Task1 task1) {
        this.task1 = task1;
    }

    @Override
    public void run() {
        super.run();
        task1.otherMethod();
    }
}


public class _2Main {
    public static void main(String[] args) {
        Task1 task1 = new Task1();
        myThread3 m1 = new myThread3(task1);
        m1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread4 m2 = new myThread4(task1);
        m2.start();

    }
}