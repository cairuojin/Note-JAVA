package 第一章_线程API.守护线程;

/**
 * @author cairuojin
 * @create 2018-12-06 22:05
 */

class myThread extends Thread {
    int i = 0;
    @Override
    public void run() {
        super.run();
        while (true) {
            i++;
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        myThread tt = new myThread();
        tt.setDaemon(true);         //设置该线程为守护线程
        tt.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}