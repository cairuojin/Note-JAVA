package 第六章_单例模式与多线程._1单例模式;

/**
 * create by cairuojin on 2019/01/07
 * 延迟加载的单例模式
 */

class MyObject1{
    static private MyObject1 myObject;          //没有立即new对象
    private MyObject1() {}
    static public MyObject1 getMyObject() {
        if(myObject == null){
            try {
                Thread.sleep(1000);      //假设准备对象需要时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject = new MyObject1();         //new对象
        }
        return myObject;
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        System.out.println(MyObject1.getMyObject().hashCode());
    }
}

public class _2Main {
    public static void main(String[] args) {
        MyThread2 t1 = new MyThread2();
        MyThread2 t2 = new MyThread2();
        MyThread2 t3 = new MyThread2();
        t1.start();t2.start();t3.start();
    }
}
