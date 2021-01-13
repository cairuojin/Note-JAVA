package 第三章_线程间通信._1等待通知机制;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cairuojin on 2018/12/27
 * 实验size = 5 时，退出
 */

class MyList1{
    static private List list = new ArrayList();
    static public void add(){
        list.add("anything");
    }
    static public int getSize(){
        return list.size();
    }
}
class MyThread5 extends Thread{
    private Object lock;
    public MyThread5(Object object) {
        this.lock = object;
    }
    @Override
    public void run() {
        System.out.println("进入线程1");
        try{
            synchronized (lock){
                for(int i = 0; i < 10; i++){
                    MyList1.add();
                    if(MyList1.getSize() == 5){
                        lock.notify();
                        System.out.println("==5 发出通知");
                    }
                    System.out.println("添加了" + (i + 1) + "个元素");
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

class MyThread6 extends Thread{
    private Object lock;
    public MyThread6(Object lock) {
        this.lock = lock;
    }
    @Override
    public void run() {
        System.out.println("进入线程2");
        try {
            synchronized (lock){
                if(MyList1.getSize() != 5 ){
                    System.out.println("开始wait" + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("停止wait" + System.currentTimeMillis());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _4Main {
    public static void main(String[] args) {
        Object lock = new Object();
        MyThread5 tt1 = new MyThread5(lock);
        MyThread6 tt2 = new MyThread6(lock);
        tt2.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tt1.start();
    }
}
