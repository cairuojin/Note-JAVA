package 第六章_单例模式与多线程._3static代码块的实现;

/**
 * create by cairuojin on 2019/01/07
 */

class MyObject{
    private static MyObject instance = null;

    private MyObject(){}

    static {
        instance = new MyObject();
    }

    public static MyObject getInstance(){
        return instance;
    }
}
public class _1Main {
}
