package com.example.demo.old.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-29
 * @time 11:09
 */
public class LazyModel_2 {

    private static LazyModel_2 lazyModel_2 = null;

    public static LazyModel_2 getInstance() {
        if (lazyModel_2 == null) {
            synchronized (LazyModel_2.class) {
                // 锁加在这里，锁的粒度最小，但是也不能解决问题，原因是当两个线程同时来了，进了if判断后都是null，
                // 都会争取锁也就是临界区资源，这样第一个线程new了一个对象，但是第二个依然会new一个
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lazyModel_2 = new LazyModel_2();
            }

        }
        return lazyModel_2;
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i=0; i<100; i++){
            Thread thread = new Thread() {
                @Override
                public void run() {
                    System.out.println(LazyModel_2.getInstance().hashCode());
                    countDownLatch.countDown();
                }
            };
            thread.start();
        }
    }
}
