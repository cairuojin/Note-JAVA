package 第二章_对象及变量的并发访问._1synchronized同步方法;

/**
 * create by cairuojin on 2018/12/07
 * 内部锁重入
 */
class cat{
    synchronized public void eat(){     //eat方法内部可以利用锁重入机制，访问say方法
        System.out.println("eat");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        say();
    }
    synchronized public void say(){
        System.out.println("say");
    }
}

class myThreadE extends Thread{
    private cat c;
    public myThreadE(cat c) {
        this.c = c;
    }
    @Override
    public void run() {
        super.run();
        c.eat();
    }
}

public class _3Main {

    public static void main(String[] args) {
        myThreadE m1 = new myThreadE(new cat());
        m1.start();
    }
}
