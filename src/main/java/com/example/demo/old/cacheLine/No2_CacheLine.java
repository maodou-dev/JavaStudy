package com.example.demo.old.cacheLine;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-29
 * @time 10:02
 */
public class No2_CacheLine {
    private final static long COUNT = 100_000_000L;

    public static class T{
        // 一个cache line的大小为64个bytes，long类型占用8个bytes，
        // 所以这里在X的前后都定义7个long类型的空数据，目的是让数组中的两个有用数据不在同一个cache line，
        // 这样会使两个线程thread1和thread2不会同时读取到数组中的两个值，因此volatile通知其他线程的时候便无需等待，
        // 从而提高效率，这里的X必须要用volatile修饰，不然不满足可见性，导致不会进行通知，从而导致试验失败
        long p1,p2,p3,p4,p5,p6,p7;
        volatile long X = 0L;
        long p9,p10,p11,p12,p13,p14,p15;
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
            System.out.println(end - begin); // 1087
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
