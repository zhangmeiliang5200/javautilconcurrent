package com.example;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created by zhangml on 2017/11/27.
 */
public class MultiThead {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(" [" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }

        while (true){
            System.out.println("Hello");
        }
    }
}
