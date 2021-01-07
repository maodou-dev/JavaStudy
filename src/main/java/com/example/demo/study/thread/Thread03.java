package com.example.demo.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/6
 * @time 20:10
 */
public class Thread03 {
    // TODO 线程池的学习
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Thread03 thread03 = new Thread03();
        // FIXME 随便写的，不正规
        thread03.executorService.execute(new Thread(() -> {
            System.out.println("1");
        }));

        thread03.executorService.shutdown();
    }
}
