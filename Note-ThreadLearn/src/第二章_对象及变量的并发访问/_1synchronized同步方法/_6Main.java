package 第二章_对象及变量的并发访问._1synchronized同步方法;

/**
 * @author cairuojin
 * @create 2018-12-08 9:21
 * 同步不具有继承性
 */

class father1{
    synchronized public void fatherMethod(){
        System.out.println("fatherMethod : " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("fatherMethod : " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
    }
}
class child1 extends father1{
    @Override
    public void fatherMethod() {        //继承不带有synchronized    有需要自己加
        System.out.println("childMethod : " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("childMethod  : " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        super.fatherMethod();
    }
}

class myMethodH extends Thread{
    private child1 cc;

    public myMethodH(child1 cc) {
        this.cc = cc;
    }

    @Override
    public void run() {
        super.run();
        cc.fatherMethod();
    }
}
class myMethodI extends Thread{
    private child1 cc;

    public myMethodI(child1 cc) {
        this.cc = cc;
    }

    @Override
    public void run() {
        super.run();
        cc.fatherMethod();
    }
}

public class _6Main {


    public static void main(String[] args) {
        child1 cc = new child1();
        myMethodH m1 = new myMethodH(cc);
        m1.start();
        myMethodI m2 = new myMethodI(cc);
        m2.start();
    }
}