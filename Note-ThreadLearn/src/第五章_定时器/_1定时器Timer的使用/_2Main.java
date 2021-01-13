package 第五章_定时器._1定时器Timer的使用;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create by cairuojin on 2019/01/07\
 * 一个Timer中有多个task
 */


public class _2Main {
    static class MyTask1 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行任务1" + new Date().toLocaleString() + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("结束任务1" + new Date().toLocaleString() + " " + Thread.currentThread().getName());
        }
    }
    static class MyTask2 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行任务2" + new Date().toLocaleString() + " " + Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time1 = "2019-1-7 15:19:20";
        Date parse1 = simpleDateFormat.parse(time1);
        String time2 = "2019-1-7 15:19:25";
        Date parse2 = simpleDateFormat.parse(time2);
        timer.schedule(new MyTask1(),parse1);
        timer.schedule(new MyTask2(),parse2);
    }
}
