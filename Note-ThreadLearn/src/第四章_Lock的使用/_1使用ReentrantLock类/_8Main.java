package 第四章_Lock的使用._1使用ReentrantLock类;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by cairuojin on 2019/01/07
 */

class run {
    volatile private static int nextPrintWho = 1;                       //用此值判断线程是否该执行
    private static ReentrantLock lock = new ReentrantLock();            //锁
    final private static Condition conditionA = lock.newCondition();    //三个条件
    final private static Condition conditionB = lock.newCondition();
    final private static Condition conditionC = lock.newCondition();

    public static void main(String[] args) {
        Thread threadA = new Thread() {      //三个线程分别加锁打印3个值（保证123能连续打印）
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 1) {  //判断该线程是否该执行
                        conditionA.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadA" + (i + 1));
                    }
                    nextPrintWho = 2;
                    conditionB.signalAll();      //唤醒下一个任务（不保证下个任务能抢占到CPU，所以需要nextPrintWho判断是否该继续）
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };


        Thread threadB = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 2) {
                        conditionB.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadB" + (i + 1));
                    }
                    nextPrintWho = 3;
                    conditionC.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread threadC = new Thread() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    while (nextPrintWho != 3) {
                        conditionC.await();
                    }
                    for (int i = 0; i < 3; i++) {
                        System.out.println("ThreadC" + (i + 1));
                    }
                    nextPrintWho = 1;
                    conditionA.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        };


        Thread[] aThreads = new Thread[5];
        Thread[] bThreads = new Thread[5];
        Thread[] cThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            aThreads[i] = new Thread(threadA);
            bThreads[i] = new Thread(threadB);
            cThreads[i] = new Thread(threadC);
            aThreads[i].start();
            bThreads[i].start();
            cThreads[i].start();
        }
    }
}
public class _8Main {
}
