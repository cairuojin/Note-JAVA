package 第三章_线程间通信._1等待通知机制;

/**
 * create by cairuojin on 2019/01/02
 * 一生产者对应一消费者
 */
class ValueObject {
    public static String value = "";
}

//生产者
class P {
    private String lock;

    public P(String lock) {
        this.lock = lock;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                if (!ValueObject.value.equals("")) {      //值不为空的时候等待
                    System.out.println("生产者wait了●" + Thread.currentThread().getName());
                    lock.wait();
                }
                System.out.println("生产者run了●" + Thread.currentThread().getName());
                String value = System.currentTimeMillis() + "_" + System.nanoTime();    //为空 生产
                System.out.println("set的值是" + value);
                ValueObject.value = value;
                lock.notifyAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//消费者
class C {
    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                if (ValueObject.value.equals("")) {       //值为空的时候等待
                    System.out.println("消费者wait了★" + Thread.currentThread().getName());
                    lock.wait();
                }
                System.out.println("消费者run了★" + Thread.currentThread().getName());
                System.out.println("get的值是" + ValueObject.value);   //不为空 消费
                ValueObject.value = "";
                lock.notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread11 extends Thread {
    private P p;

    public MyThread11(P p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true)
            p.setValue();
    }
}

class MyThread12 extends Thread {
    private C c;

    public MyThread12(C c) {
        this.c = c;
    }

    @Override
    public void run() {
        while (true)
            c.getValue();
    }
}

public class _7Main {
    public static void main(String[] args) {
        //一对一生产消费
//        String lock = new String("");
//        P p = new P(lock);
//        C c = new C(lock);
//        MyThread11 t1 = new MyThread11(p);
//        MyThread12 t2 = new MyThread12(c);
//        t1.start();
//        t2.start();


        //多个生产消费则会出现假死
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        MyThread11[] pThread = new MyThread11[2];
        MyThread12[] cThread = new MyThread12[2];
        for (int i = 0; i < 2; i++) {
            pThread[i] = new MyThread11(p);
            pThread[i].setName("生产者" + (i + 1));
            cThread[i] = new MyThread12(c);
            cThread[i].setName("消费者" + (i + 1));
            pThread[i].start();
            cThread[i].start();
        }


    }
}
