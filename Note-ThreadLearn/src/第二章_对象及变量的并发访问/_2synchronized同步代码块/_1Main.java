package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * @author cairuojin
 * @create 2018-12-09 11:07
 * 使用synchronized同步方法块
 */

class Task{
    private String username;
    private String password;
    public void doTask(){
        try {
            System.out.println("start task" + Thread.currentThread().getName());
            Thread.sleep(5000);
            String a = "myusername";
            String b = "mypassword";
            synchronized (this){        //内部同步
                username = a;
                password = b;
            }
            System.out.println(username + " " + Thread.currentThread().getName());
            System.out.println(password + " " + Thread.currentThread().getName());
            System.out.println("end task" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class myThread1 extends Thread{
    private Task task;
    public myThread1(Task task) {
        this.task = task;
    }
    @Override
    public void run() {
        super.run();
        task.doTask();
    }
}
class myThread2 extends Thread{
    private Task task;
    public myThread2(Task task) {
        this.task = task;
    }
    @Override
    public void run() {
        super.run();
        task.doTask();
    }
}
public class _1Main {
    public static void main(String[] args) {
        Task task = new Task();
        myThread1 m1 = new myThread1(task);
        m1.start();
        myThread2 m2 = new myThread2(task);
        m2.start();
    }
}