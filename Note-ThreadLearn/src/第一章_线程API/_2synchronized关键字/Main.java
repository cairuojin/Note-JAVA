package 第一章_线程API._2synchronized关键字;


class Mythread extends Thread {
    private int ticket = 5;
    @Override
    synchronized public void run() {
        super.run();
        System.out.println(this.currentThread().getName() + "卖票：" + ticket);
        ticket--;
    }
}
public class Main {
    public static void main(String[] args) {
        Mythread m1 = new Mythread();
        new Thread(m1).start();
        new Thread(m1).start();
        new Thread(m1).start();
        new Thread(m1).start();
        new Thread(m1).start();
    }
}