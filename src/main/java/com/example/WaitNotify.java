package com.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * Created by zhangml on 2017/11/16.
 */
public class WaitNotify {
    static boolean flag=true;
    static Object lock=new Object();

    public static void main(String[] args) throws Exception {

        Thread waitThread=new Thread(new Wait(),"WaitThread");
        waitThread.start();
        Thread.sleep(1000);
        Thread notifyThread=new Thread(new Notify(),"NotifyThread");
        notifyThread.start();

    }

    static  class Wait implements  Runnable{
        public void  run(){
            //加锁
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    }catch (InterruptedException ex){

                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. running @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static  class  Notify implements Runnable{
        public void  run(){
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
