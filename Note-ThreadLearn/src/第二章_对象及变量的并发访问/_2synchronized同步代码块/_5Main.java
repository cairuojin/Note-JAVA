package 第二章_对象及变量的并发访问._2synchronized同步代码块;

/**
 * @author cairuojin
 * @create 2018-12-09 21:01
 */

class myObject{
    synchronized public void printString(){
        System.out.println("obj 内部方法锁 " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
        System.out.println("-----");
        System.out.println("obj 内部方法解锁 " + System.currentTimeMillis() + " " + Thread.currentThread().getName());
    }
}
class Service1{
    public void testMethod1(myObject object){
        synchronized (object){
            try {
                System.out.println("synchronized锁了obj对象" + System.currentTimeMillis() + " " + Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("synchronized解锁了obj对象" + System.currentTimeMillis() + " " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class myThread9 extends Thread{
    private Service1 service1;
    private myObject myObject;

    public myThread9(Service1 service1, myObject myObject) {
        this.service1 = service1;
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        service1.testMethod1(myObject);
    }
}
class myThread10 extends Thread{
    private myObject myObject;

    public myThread10(myObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        super.run();
        myObject.printString();
    }
}
public class _5Main {
    public static void main(String[] args) {
        Service1 service1 = new Service1();
        myObject obj = new myObject();
        myThread9 m1 = new myThread9(service1,obj);
        m1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread10 m2 = new myThread10(obj);
        m2.start();
    }
}