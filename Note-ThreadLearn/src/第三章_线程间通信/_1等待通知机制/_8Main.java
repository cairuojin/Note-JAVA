package 第三章_线程间通信._1等待通知机制;

import java.util.ArrayList;
import java.util.List;

/**
 * create by cairuojin on 2019/01/02
 * 消费者和生产者操作栈
 */

class MyStack{
    private List list = new ArrayList();

    //入栈
    synchronized public void push(){
        try{
            while (list.size() == 1){
                this.wait();
            }
            list.add(Math.random());
            this.notifyAll();
            System.out.println("push = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //出栈
    synchronized public String pop(){
        String returnValue = "";
        try{
            while(list.size() == 0){
                System.out.println("pop操作" + Thread.currentThread().getName() + "wait");
                this.wait();
            }
            returnValue = "" + list.get(0);
            list.remove(0);
            this.notifyAll();
            System.out.println("pop = " + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}

class P1{
    private MyStack myStack;
    public P1(MyStack myStack) {
        this.myStack = myStack;
    }
    public void pushService(){
        myStack.push();
    }
}

class C1{
    private MyStack myStack;
    public C1(MyStack myStack) {
        this.myStack = myStack;
    }
    public void popService(){
        myStack.pop();
    }
}



class MyThread13 extends Thread{
    private P1 p1;
    public MyThread13(P1 p1) {
        this.p1 = p1;
    }
    @Override
    public void run() {
        while (true)
            p1.pushService();
    }
}

class MyThread14 extends Thread{
    private C1 c1;
    public MyThread14(C1 c1) {
        this.c1 = c1;
    }
    @Override
    public void run() {
        while (true)
            c1.popService();
    }
}

public class _8Main {

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        P1 p1 = new P1(myStack);
        C1 c1 = new C1(myStack);
        MyThread13 myThread13 = new MyThread13(p1);
        MyThread14 cT1 = new MyThread14(c1);
        MyThread14 cT2 = new MyThread14(c1);
        MyThread14 cT3 = new MyThread14(c1);
        MyThread14 cT4 = new MyThread14(c1);
        MyThread14 cT5 = new MyThread14(c1);
        myThread13.start();
        cT1.start();
        cT2.start();
        cT3.start();
        cT4.start();
        cT5.start();
    }
}
