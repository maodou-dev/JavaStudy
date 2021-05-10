package com.example.demo.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/6
 * @time 17:04
 */
public class Lock02 {
    // lock 是可以被打断的，synchronized是不能被打断的
    Lock lock = new ReentrantLock();
    public Integer count = 0;


    public static void main(String[] args) {
        Lock02 lock02 = new Lock02();

        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2-start");
                lock02.lock.lockInterruptibly();
                System.out.println("t2-end");
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            }
        });
        Thread t1 = new Thread(() -> {
            try {
                lock02.lock.lock();
                for (int i = 0; i < 10; i++) {
                    System.out.println(++lock02.count);
                    TimeUnit.SECONDS.sleep(1);
                    if (lock02.count == 5) {
                        t2.interrupt();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock02.lock.unlock();
            }
        });
        t1.start();
        t2.start();

    }
}
