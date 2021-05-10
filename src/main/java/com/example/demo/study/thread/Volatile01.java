package com.example.demo.study.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/5
 * @time 14:43
 */
public class Volatile01 {
    // volatile是不能替代synchronized
    // 因为volatile具有可见性，但是不具有原子性，而synchronized是具有原子性、可见性和有序性
    public volatile static Integer COUNT = 0;

    public static class ThreadTest implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                increase();
            }
        }
    }

    public static synchronized void increase() {
        COUNT++;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new ThreadTest()));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(COUNT);
    }
}
