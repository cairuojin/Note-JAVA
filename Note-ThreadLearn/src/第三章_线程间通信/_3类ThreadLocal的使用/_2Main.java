package 第三章_线程间通信._3类ThreadLocal的使用;

import java.util.Date;

/**
 * create by cairuojin on 2019/01/02
 * 线程继承，继承ThreadLocal的值
 */

class InheritableThreadLocalExt extends InheritableThreadLocal{ //使该对象具有初始值
    @Override
    protected Object initialValue() {
        return new Date().getTime();
    }

//    @Override
//    protected Object childValue(Object parentValue) { //覆写此方法可以在子线程调用时修改值（parentValue为init时候的值）
//        return parentValue + "纸箱厂";
//    }
}

class Tools1{
    public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
}

class MyThread7 extends Thread{
    @Override
    public void run() {
        try{
            for(int i = 0; i < 10; i++){
                System.out.println("A get :" + Tools1.tl.get());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class _2Main {
    public static void main(String[] args) {
        try{
            for(int i = 0; i < 10; i++){
                System.out.println("Main get :" + Tools1.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5500);
            MyThread7 tt = new MyThread7();
            tt.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
