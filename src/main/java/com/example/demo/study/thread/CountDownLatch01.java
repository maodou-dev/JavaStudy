package com.example.demo.study.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/7
 * @time 14:27
 */
public class CountDownLatch01 {
    private static final Integer COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        // CountDownLatch比较简单，简单理解为是一个等待线程执行的工具，当count为0时，即可解除await，继续执行下去
        CountDownLatch latch = new CountDownLatch(COUNT);
        Object lock = new Object();
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                latch.countDown();
                latch.countDown();
                latch.countDown();
                latch.countDown();
            }).start();
        }
        System.out.println("2");
        latch.await();
        System.out.println(latch.getCount());

        System.out.println("1");

    }
}
