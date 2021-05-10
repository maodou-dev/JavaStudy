package com.example.demo.study.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/7
 * @time 14:34
 */
public class LongAdder01 {
    // 分段锁
    LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        LongAdder01 longAdder01 = new LongAdder01();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                longAdder01.longAdder.increment();
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
            System.out.println(longAdder01.longAdder);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
