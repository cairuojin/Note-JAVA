package 第二章_对象及变量的并发访问._2synchronized同步代码块;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cairuojin
 * @create 2018-12-09 20:07
 * 用synchronized解决脏读问题
 */
class myOneList{
    private List list = new ArrayList();
    synchronized public void add(String data){
        list.add(data);
    }
    synchronized public int getSize(){
        return list.size();
    }
}
class myService{
    public myOneList addServiceMethod(myOneList list,String data){
        try{
            synchronized (list){
                if(list.getSize() < 1){         //锁同一个对象list，同步判断getsize，不锁则异步判断，导致size = 2
                    Thread.sleep(2000);
                    list.add(data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }
}

class myThread7 extends Thread{
    private myOneList list;
    public myThread7(myOneList list) {
        this.list = list;
    }
    @Override
    public void run() {
        super.run();
        myService service = new myService();
        service.addServiceMethod(list, "A");
    }
}
class myThread8 extends Thread{
    private myOneList list;
    public myThread8(myOneList list) {
        this.list = list;
    }
    @Override
    public void run() {
        super.run();
        myService service = new myService();
        service.addServiceMethod(list, "B");
    }
}


public class _4Main {
    public static void main(String[] args) {
        myOneList list = new myOneList();
        myThread7 m1 = new myThread7(list);
        m1.start();
        myThread8 m2 = new myThread8(list);
        m2.start();
        try {
            Thread.sleep(4000);
            int size = list.getSize();
            System.out.println(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}