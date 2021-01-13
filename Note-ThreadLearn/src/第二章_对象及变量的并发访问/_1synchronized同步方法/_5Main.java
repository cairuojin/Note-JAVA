package 第二章_对象及变量的并发访问._1synchronized同步方法;

/**
 * create by cairuojin on 2018/12/07
 * 异常释放锁
 */

class exectionMethod{
    synchronized public void method() throws Exception {
        int i = 0;
        while (true){
            if("a".equals(Thread.currentThread().getName())){
                i++;
                System.out.println(i + " a ...");
                Thread.sleep(1000);
                if(i == 10){
                    throw new Exception("aaaaa");           //线程出现异常，自动释放锁
                }
            } else {
                System.out.println("BBBBB");
                break;
            }
        }
    }
}
class myThreadG extends Thread{
    private exectionMethod ee;

    public myThreadG(exectionMethod ee) {
        this.ee = ee;
    }

    @Override
    public void run() {
        try {
            ee.method();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


public class _5Main {


    public static void main(String[] args) {
        exectionMethod ee = new exectionMethod();
        myThreadG mm1 = new myThreadG(ee);
        mm1.setName("a");
        mm1.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThreadG mm2 = new myThreadG(ee);
        mm2.setName("b");
        mm2.start();
    }



}
