package com.example.demo.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/6
 * @time 17:29
 */
public class Lock04 {
    // lock可以设置尝试获取锁，超时则放弃锁
    Lock lock = new ReentrantLock();
    Boolean haveLock = false;
    public void m1() {
        try {
            lock.lock();
            for (int i = 0; i < 2; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void m2() {
        try {
            haveLock = lock.tryLock(3, TimeUnit.SECONDS);
            System.out.println(haveLock + " - t2");
        } catch (InterruptedException e) {
            System.out.println("catch");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Lock04 lock04 = new Lock04();
        Thread t1 = new Thread(lock04::m1);
        Thread t2 = new Thread(lock04::m2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
