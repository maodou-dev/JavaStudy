package com.example.demo.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 4种线程池
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/2/3
 * @time 15:25
 */
public class ThreadPool01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService1 = Executors.newFixedThreadPool(3);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("1");
            }
        }, 3, TimeUnit.SECONDS); // 延迟3秒执行
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("2");
            }
        }, 1, 3, TimeUnit.SECONDS); // 延迟一秒后，每三秒执行

        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
    }
}
