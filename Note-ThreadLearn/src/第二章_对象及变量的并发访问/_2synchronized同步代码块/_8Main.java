package 第二章_对象及变量的并发访问._2synchronized同步代码块;

import 第二章_对象及变量的并发访问._2synchronized同步代码块.PublicClass.InnerClass1;
import 第二章_对象及变量的并发访问._2synchronized同步代码块.PublicClass.InnerClass2;

/**
 * create by cairuojin on 2018/12/10
 * 内部类与同步（与普通类锁机制相同）
 */

class PublicClass {
    static class InnerClass1 {

        public void method1(InnerClass2 class2) {
            String threadName = Thread.currentThread().getName();
            synchronized (class2) {                                         //锁inner2对象
                System.out.println(threadName + " 进入inner Class1类中的method1方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println(threadName + " " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName + " 离开inner Class1类中的method1方法");
            }
        }

        public synchronized void method2() {            //锁this对象即inner1对象
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 进入inner Class1类中的method2方法");
            for (int j = 0; j < 10; j++) {
                System.out.println(threadName + " " + j);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + "离开inner Class1类中的method2方法");
        }
    }

    static class InnerClass2 {
        public synchronized void method1() {        //锁this对象，即inner2对象，所以此方法和上面锁inner2对象的方法将同步执行
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " 进入inner Class2类中的method1方法");
            for (int i = 0; i < 10; i++) {
                System.out.println(threadName + " " + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " 离开inner Class2类中的method1方法");
        }
    }
}


public class _8Main {

    public static void main(String[] args) {
        InnerClass1 in1 = new InnerClass1();
        InnerClass2 in2 = new InnerClass2();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                in1.method1(in2);
            }
        }, "T1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                in1.method2();
            }
        }, "T2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                in2.method1();
            }
        }, "T3");
        t1.start();
        t2.start();
        t3.start();
    }
}
