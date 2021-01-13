package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * @author cairuojin
 * @create 2018-12-09 19:47
 * 锁anyString
 */

class Service{
    private String usernameParam;
    private String passwordParam;
    private String anyString = new String();
    public void setParams(String username,String password){
        try {
            synchronized (anyString){
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " 进入同步方法块");
                usernameParam = username;
                Thread.sleep(3000);
                passwordParam = password;
                System.out.println(Thread.currentThread().getName() + " " + System.currentTimeMillis() + " 退出同步方法块");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class myThread5 extends Thread{
    private Service service;
    public myThread5(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.setParams("a","aaa" );
    }
}
class myThread6 extends Thread{
    private Service service;
    public myThread6(Service service) {
        this.service = service;
    }
    @Override
    public void run() {
        super.run();
        service.setParams("b","bbb" );
    }
}
public class _3Main {
    public static void main(String[] args) {
        Service service = new Service();
        myThread5 m1 = new myThread5(service);
        m1.start();
        myThread6 m2 = new myThread6(service);
        m2.start();
    }
}