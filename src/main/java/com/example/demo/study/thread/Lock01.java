package com.example.demo.study.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/6
 * @time 16:43
 */
public class Lock01 {
    // lock 可重入锁，synchronized也是可重入锁，而且synchronized必须是可重入锁
    Lock lock = new ReentrantLock();
    public Integer count = 0;

    public void m() {
        try {
            lock.lock();
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        Lock01 lock01 = new Lock01();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(lock01::m);
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(lock01.count);
    }

}
