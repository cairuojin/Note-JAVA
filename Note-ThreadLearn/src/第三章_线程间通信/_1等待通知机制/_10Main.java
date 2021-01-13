package 第三章_线程间通信._1等待通知机制;

/**
 * create by cairuojin on 2019/01/02
 * 等待/通知交叉备份  假设需要有20个线程，分别将数据备份到A/B两个库，且轮流交替进行
 */

class DBTools{
    volatile private boolean prevIsA = false;
    synchronized public void backupA(){
        try{
            while (prevIsA == true){
                wait();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println("★★★★★");        //假设备份A库操作
            }
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public void backupB(){
        try{
            while (prevIsA == false){
                wait();
            }
            for(int i = 0; i < 5; i ++){
                System.out.println("●●●●●");        //假设备份B库操作
            }
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread17 extends Thread{
    private DBTools dbTools;
    public MyThread17(DBTools dbTools) {
        this.dbTools = dbTools;
    }
    @Override
    public void run() {
        super.run();
        dbTools.backupA();
    }
}
class MyThread18 extends Thread{
    private DBTools dbTools;
    public MyThread18(DBTools dbTools) {
        this.dbTools = dbTools;
    }
    @Override
    public void run() {
        super.run();
        dbTools.backupB();
    }
}
public class _10Main {

    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for(int i = 0; i < 20; i ++){
            MyThread17 output = new MyThread17(dbTools);
            output.start();
            MyThread18 input = new MyThread18(dbTools);
            input.start();
        }
    }
}
