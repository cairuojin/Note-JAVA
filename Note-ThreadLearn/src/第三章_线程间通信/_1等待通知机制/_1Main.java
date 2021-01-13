package 第三章_线程间通信._1等待通知机制;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cairuojin
 * @create 2018-12-17 22:31
 * 使用sleep和while(true)实现通信
 */

class MyList{
    private List list = new ArrayList();
    public void add(){
        list.add("蔡");
    }
    public int size(){
        return list.size();
    }
}

class MyThread1 extends Thread{
    private MyList list;

    public MyThread1(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++){
                list.add();
                System.out.println("添加元素" + (i + 1));  //每一秒+1
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 extends Thread{
    private MyList list;

    public MyThread2(MyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true){
                System.out.println(list.size());
                if (list.size() == 5){
                    System.out.println("size == 5 退出");     //size == 5时退出此线程，这里用while true会浪费CPU资源
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _1Main {
    public static void main(String[] args) {
        MyList service = new MyList();
        MyThread1 a = new MyThread1(service);
        a.start();
        MyThread2 b = new MyThread2(service);
        b.start();
    }
}