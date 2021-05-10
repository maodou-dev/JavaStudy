package com.example.demo.study.thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/8
 * @time 16:27
 */
public class Semaphore01 {
    // 信号灯
    // permits 信号量，代表同时允许几个线程访问
    Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        Semaphore01 semaphore01 = new Semaphore01();
        Thread t1 = new Thread(() -> {
            try {
                // 等待，如果semaphore的信号量不为0，则继续执行
                semaphore01.semaphore.acquire();
                System.out.println("t1");
                TimeUnit.SECONDS.sleep(5);
                //System.out.println("t1");
                // 把semaphore的信号量返回1
                semaphore01.semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread t2 = new Thread(() -> {
            try {
                semaphore01.semaphore.acquire();
                System.out.println("t2");
                TimeUnit.SECONDS.sleep(5);
                semaphore01.semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
