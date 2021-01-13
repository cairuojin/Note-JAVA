package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * create by cairuojin on 2018/12/10
 * 死锁的出现
 */

class DealThread implements Runnable{
    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();
    public void setFlag(String username){
        this.username = username;
    }
    @Override
    public void run() {
        if("a".equals(username)){
            synchronized (lock1){
                try {
                    System.out.println("username = " + username);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("lock1 - > lock2");
                }
            }
        }
        if("b".equals(username)){
            synchronized (lock2){
                try{
                    System.out.println("username = " + username);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1){
                    System.out.println("lock2 - > lock1");
                }
            }
        }
    }
}


public class _7Main {

    public static void main(String[] args) {
        DealThread dl = new DealThread();
        dl.setFlag("a");
        new Thread(dl).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dl.setFlag("b");
        new Thread(dl).start();
    }


}
