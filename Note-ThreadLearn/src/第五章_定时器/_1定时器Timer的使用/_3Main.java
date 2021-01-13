package 第五章_定时器._1定时器Timer的使用;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create by cairuojin on 2019/01/07
 * 周期性执行
 */
public class _3Main {

    static class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行任务" + new Date().toLocaleString() + " " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = "2019-1-7 15:29:20";
        Date parse = simpleDateFormat.parse(time);
        timer.schedule(new MyTimerTask(), parse , 4000);
    }

}
