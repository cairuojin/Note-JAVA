package 第二章_对象及变量的并发访问._1synchronized同步方法;

/**
 * create by cairuojin on 2018/12/07
 * 访问无synchronization的方法
 */

class Person {
    int i = 0;
    synchronized public void say() {        //对象Lock后，仍可访问该对象未加锁的方法 例如eat
        System.out.println("say..." + i);
        i++;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("say..." + i);
    }
    public void eat() {
        System.out.println("eat..." + i);
        i++;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("eat..." + i);
    }
}

class myThreadC extends Thread {
    private Person p;

    public myThreadC(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        p.say();
    }
}

class myThreadD extends Thread {
    private Person p;

    public myThreadD(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        super.run();
        p.eat();
    }
}


public class _2Main {
    public static void main(String[] args) {
        Person p = new Person();
        myThreadC m1 = new myThreadC(p);
        m1.start();
        myThreadD m2 = new myThreadD(p);
        m2.start();
    }

}
