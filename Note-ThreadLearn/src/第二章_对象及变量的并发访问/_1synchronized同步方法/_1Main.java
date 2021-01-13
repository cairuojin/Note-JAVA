package 第二章_对象及变量的并发访问._1synchronized同步方法;

/**
 * create by cairuojin on 2018/12/07
 * synchronization关键字
 */

class Usermessage{
    int num = 0;
    synchronized public void addI (String username){
        try{
            if("a".equals(username)){
                num = 100;
                System.out.println("a set over");       //先让a线程进入休眠，b线程则进入不了，验证synchronized关键字
                Thread.sleep(1000);
            } else {
                num = 200;
                System.out.println("b set over");
            }
            System.out.println(username + " :" + num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class myThreadA extends Thread{
    private Usermessage uu;
    public myThreadA(Usermessage uu) {
        this.uu = uu;
    }
    @Override
    public void run() {
        super.run();
        uu.addI("a");
    }
}

class myThreadB extends Thread{
    private Usermessage uu;
    public myThreadB(Usermessage uu) {
        this.uu = uu;
    }
    @Override
    public void run() {
        super.run();
        uu.addI("b");
    }
}


public class _1Main {

    public static void main(String[] args) {
        Usermessage uu1 = new Usermessage();
        Usermessage uu2 = new Usermessage();
        myThreadA aa = new myThreadA(uu1);
        myThreadB bb = new myThreadB(uu2);
        aa.start();
        bb.start();
    }
}
