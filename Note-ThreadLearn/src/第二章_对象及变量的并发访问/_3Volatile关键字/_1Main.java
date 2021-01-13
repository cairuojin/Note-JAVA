package 第二章_对象及变量的并发访问._3volatile关键字;

/**
 * create by cairuojin on 2018/12/17
 *
 */
class MyThread1 implements Runnable{
    private boolean isContinuePrinf = true;     //输出标志
    public void setContinuePrinf(boolean continuePrinf) {   //set方法
        isContinuePrinf = continuePrinf;
    }
    public void prinfMethod(){
        try {
            while (isContinuePrinf == true){
                System.out.println("运行输出方法：" + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        prinfMethod();
    }
}
public class _1Main {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        Thread tt = new Thread(myThread1);
        tt.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread1.setContinuePrinf(false);
    }
}
