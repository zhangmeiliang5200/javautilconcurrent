package com.example;


import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zhangml on 2017/12/4.
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBERS = 10;

    private static final int DEFAULT_WORKER_NUMBERS = 5;

    private static final int MIN_WORKERS_NUMBERS = 1;

    private final LinkedList<Job> jobs = new LinkedList<Job>();

    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private int workerNum = DEFAULT_WORKER_NUMBERS;

    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWokers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? num : MAX_WORKER_NUMBERS;
    }

    @Override
    public void execute(Job job) {
        if(null!=job){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int num) {
        synchronized (jobs) {
            // 限制新增的Worker数量不能超过最大值
            if (num + this.workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWokers(num);
            this.workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= this.workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            // 按照给定的数量停止Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return jobs.size();
    }
    // 初始化线程工作者
    private void initializeWokers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }

    // 工作者，负责消费任务
    class Worker implements Runnable{

        private volatile boolean running=true;

        public void  run(){
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        }catch (InterruptedException ex){
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job =jobs.removeFirst();
                }
                if(job != null){
                    try{
                        job.run();
                    }catch (Exception ex){

                    }
                }
            }
        }

        public  void  shutdown(){
            running=false;
        }
    }

}
