package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * @author cairuojin
 * @create 2018-12-09 22:02
 */
class Service2 {
    synchronized public static void pringA() {
        try {
            System.out.println(Thread.currentThread().getName() + " 进入printA " + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " 离开printA " + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println(Thread.currentThread().getName() + " 进入printB " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " 离开printB " + System.currentTimeMillis());
    }

    synchronized public void printC() {     //此方法取得对象锁
        System.out.println(Thread.currentThread().getName() + " 进入printC " + System.currentTimeMillis());
        System.out.println(Thread.currentThread().getName() + " 离开printC " + System.currentTimeMillis());
    }
}

class myThread11 extends Thread {
    private Service2 service2;

    public myThread11(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        super.run();
        service2.pringA();
    }
}

class myThread12 extends Thread {
    private Service2 service2;

    public myThread12(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        super.run();
        service2.printB();
    }
}

class myThread13 extends Thread {
    private Service2 service2;

    public myThread13(Service2 service2) {
        this.service2 = service2;
    }

    @Override
    public void run() {
        super.run();
        service2.printC();
    }
}

public class _6Main {

    public static void main(String[] args) {
        Service2 service2 = new Service2();
        myThread11 m1 = new myThread11(service2);
        m1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread12 m2 = new myThread12(service2);
        m2.start();
        myThread13 m3 = new myThread13(service2);
        m3.start();
    }
}