package _3其他API;

/**
 * create by cairuojin on 2018/11/28
 */

class myThread3 extends Thread{
    @Override
    public void run() {
        super.run();
        long l = System.currentTimeMillis();
        for(int i = 0; i < 500000; i++){
            Thread.yield();
            System.out.println(i);
            i++;
        }
        System.out.println(System.currentTimeMillis() - l);
    }
}

public class _4yieId {

    public static void main(String[] args) {
        myThread3 tt = new myThread3();
        tt.start();
    }


}
