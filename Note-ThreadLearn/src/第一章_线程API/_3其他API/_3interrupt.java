package _3其他API;

/**
 * create by cairuojin on 2018/11/28
 */
class myThread2 extends Thread{
    @Override
    public void run() {
        super.run();
        for(int i = 0; i < 500000; i++){
            if(this.isInterrupted()){
                System.out.println("退出线程");
                break;
            }
            System.out.println("i=" + (i + 1));
        }
    }
}
public class _3interrupt {
    public static void main(String[] args) {
        try {
            myThread2 tt = new myThread2();
            tt.start();
            Thread.sleep(2000);
            tt.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
