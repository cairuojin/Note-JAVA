package 第六章_单例模式与多线程._2DCL双检查锁;


/**
 * create by cairuojin on 2019/01/07
 * DCL双检查锁
 */

class MyObject {
    static private MyObject myObject;
    private MyObject() {}       //单例模式

    static public MyObject getMyObject() {
        try {
            if (myObject == null) {     //第一层检查
                Thread.sleep(1000); //准备工作
                synchronized (MyObject.class){  //锁内部创建对象
                    if(myObject == null)        //第二层检查，如果其他线程已经执行完毕创建则此时不创建
                        myObject = new MyObject();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println(MyObject.getMyObject().hashCode());
    }
}

public class _1Main {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread1 myThread2 = new MyThread1();
        MyThread1 myThread3 = new MyThread1();
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
