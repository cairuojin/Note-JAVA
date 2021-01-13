package 第五章_定时器._1定时器Timer的使用;

import 第五章_定时器._1定时器Timer的使用._3Main.MyTimerTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create by cairuojin on 2019/01/07
 * TimerTask 的cancel()方法
 */
public class _4Main {

    static class MyTimerTask1 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行任务1 " + new Date().toLocaleString() + " " + Thread.currentThread().getName());
            this.cancel();
        }
    }
    static class MyTimerTask2 extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行任务2 " + new Date().toLocaleString() + " " + Thread.currentThread().getName());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = "2019-1-7 15:35:20";
        Date parse = simpleDateFormat.parse(time);
        timer.schedule(new MyTimerTask1(), parse , 4000);
        timer.schedule(new MyTimerTask2(), parse , 4000);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }
}
