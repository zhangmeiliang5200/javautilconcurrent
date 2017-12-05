package com.example;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Created by zhangml on 2017/12/5.
 */
public class TwinsLockTest {
    @Test
    public void test() throws InterruptedException {
        int test=tryAcquireShared();
        final Lock lock = new TwinsLock();
        class Worker extends Thread {
            public void run()  {
                System.out.println(Thread.currentThread().getName());
                while (true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.lock();
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
        // 启动10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        // 每隔1秒换行
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            System.out.println();
        }
    }

    public static  int tryAcquireShared(){
        Random r = new Random(12);
        for (; ; ) {
           int i=r.nextInt();
            if(i>10){
                return  i;
            }else {
                return -1;
            }

        }
    }
}
