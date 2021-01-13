package 第三章_线程间通信._1等待通知机制;

/**
 * @author cairuojin
 * @create 2018-12-17 23:08
 * wait方法
 */
public class _2Main {
    public static void main(String[] args) {
        try {
            String newString = new String("");
            System.out.println("syn上面");
            synchronized (newString){
                System.out.println("wait上面");
                newString.wait();
                System.out.println("wait下面");   //wait下面的方法不执行
            }
            System.out.println("syn下面");        //这里也不会执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}