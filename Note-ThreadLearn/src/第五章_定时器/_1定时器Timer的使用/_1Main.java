package 第五章_定时器._1定时器Timer的使用;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * create by cairuojin on 2019/01/07
 * schedule(Date)
 */
public class _1Main {
    static class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            System.out.println("运行任务" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
        }
    }
    public static void main(String[] args) throws ParseException {
        String time = "2019-1-7 14:16:20";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parse = simpleDateFormat.parse(time);
        Timer timer = new Timer(true);
        timer.schedule(new MyTimerTask(),parse);
    }
}
