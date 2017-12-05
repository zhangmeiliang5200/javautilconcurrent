package com.example;

/**
 * Created by zhangml on 2017/11/23.
 */
public class VolatileExample {
    int a=0;
    volatile boolean flag=false;

    public  void  writer(){
        a=1;
        flag=true;
    }

    public  void  reader(){
        if(flag){
            int i=a;
            System.out.println("i: "+i);
        }
    }

    public static void main(String[] args) {
        VolatileExample test=new VolatileExample();
        test.flag=true;

    }
}
