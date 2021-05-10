package com.example.demo.old.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-29
 * @time 11:09
 */
public class LazyModel_1 {

    private static LazyModel_1 lazyModel_1 = null;

    public static synchronized LazyModel_1 getInstance() { // 加在方法上面能够解决多线程安全问题，但是粒度太大，某些业务代码不需要加锁，使得效率变慢
        if (lazyModel_1 == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lazyModel_1 = new LazyModel_1();
        }
        return lazyModel_1;
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i=0; i<100; i++){
            Thread thread = new Thread() {
                @Override
                public void run() {
                    System.out.println(LazyModel_1.getInstance().hashCode());
                    countDownLatch.countDown();
                }
            };
            thread.start();
        }
    }
}
