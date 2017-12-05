package com.example;

/**
 * Created by zhangml on 2017/11/16.
 */
public class Profiler {
    private static  final ThreadLocal<Long> TIME_THREADLOCAL=new ThreadLocal<Long>(){
        protected  Long initialValue(){
            return  System.currentTimeMillis();
        }
    };

    public static  final void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static  final  Long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws  Exception{
        Profiler.begin();
        Thread.sleep(1000);
        System.out.println("Cost: "+Profiler.end()+" mills");
    }

    public Object result=null;

    public synchronized Object get(long mills) throws InterruptedException {
        long future = System.currentTimeMillis() + mills;
        long remaining = mills;
       // 当超时大于0并且result返回值不满足要求
        while ((result == null) && remaining > 0) {
            wait(remaining);
            remaining = future - System.currentTimeMillis();
        }
        return result;
    }

}
