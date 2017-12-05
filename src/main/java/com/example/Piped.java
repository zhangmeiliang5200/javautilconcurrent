package com.example;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * Created by zhangml on 2017/11/16.
 */
public class Piped {
    public static void main(String[] args) throws  Exception{
        PipedWriter out = new PipedWriter();
        PipedReader in =new PipedReader();
        out.connect(in);
        Thread printThread=new Thread(new Print(in),"PrintThread");
        printThread.start();
        int receive=0;
        try {
            while ((receive=System.in.read())!=-1){
                out.write(receive);
                System.out.println("out");
                System.out.println((char)receive);
            }
        }finally {
            out.close();
        }


    }
    static class Print implements Runnable{
        private PipedReader in;
        public   Print(PipedReader in){
            this.in=in;
        }
        public  void  run(){
            int receive=0;
            try {
                while ((receive=in.read()) != -1){
                    System.out.println((char)receive);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
