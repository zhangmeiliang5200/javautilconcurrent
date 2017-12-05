package com.example;

/**
 * Created by zhangml on 2017/11/16.
 */
public class Join {
    public static void main(String[] args) throws  Exception {
        Thread previous=Thread.currentThread();
        for (int i=0;i<1;i++){
            Thread thread=new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous=thread;
        }
        Thread.sleep(20000);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }
    static  class  Domino implements  Runnable{
        private Thread thread;
        public  Domino(Thread thread){
            this.thread=thread;
        }
        public void  run(){
            try {
                thread.join();
            }catch (InterruptedException ex){

            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
