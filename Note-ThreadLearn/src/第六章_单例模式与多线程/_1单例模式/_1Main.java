package 第六章_单例模式与多线程._1单例模式;

/**
 * create by cairuojin on 2019/01/07
 * 立即加载的单例模式
 */

class MyObject{
    static private MyObject myObject = new MyObject();
    private MyObject() {}
    static public MyObject getMyObject() {      //有非线程安全问题，因为没有同步
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
