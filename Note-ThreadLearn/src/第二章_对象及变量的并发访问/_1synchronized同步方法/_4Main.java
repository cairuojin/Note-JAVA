package 第二章_对象及变量的并发访问._1synchronized同步方法;

/**
 * create by cairuojin on 2018/12/07
 * 父子类继承重入锁
 */

class father{
    public int i = 10;
    synchronized public void fatherMethod(){
        try{
            i--;
            System.out.println("Father : " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class child extends father{
    synchronized public void childMethod(){     //子类继承父类，可以在子类中利用锁重入机制访问父类的加锁方法
        try{
            while (i > 0){
                i--;
                System.out.println("Child :" + i);
                Thread.sleep(100);
                this.fatherMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class myThreadF extends Thread{
    private child cc;

    public myThreadF(child cc) {
        this.cc = cc;
    }
    @Override
    public void run() {
        cc.childMethod();
    }
}

public class _4Main {

    public static void main(String[] args) {
        child cc = new child();
        myThreadF mm = new myThreadF(cc);
        mm.start();
    }
}
