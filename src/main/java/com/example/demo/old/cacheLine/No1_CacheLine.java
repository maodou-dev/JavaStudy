package com.example.demo.old.cacheLine;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-29
 * @time 9:44
 */
public class No1_CacheLine {

    private final static long COUNT = 100_000_000L;


    public static class T{
        volatile long X = 0L;
    }
    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    };

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread thread1 = new Thread(){
            @Override
            public void run(){
                for (long i = 0L; i < COUNT; i++){
                    arr[0].X = i;
                }
                countDownLatch.countDown();
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run(){
                for (long i = 0L; i < COUNT; i++){
                    arr[1].X = i;
                }
                countDownLatch.countDown();
            }
        };
        try {
            long begin = System.currentTimeMillis();
            thread1.start();
            thread2.start();
            countDownLatch.await();
            long end = System.currentTimeMillis();
            System.out.println(end - begin); // 5279
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
