package com.example.demo.study.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/6
 * @time 17:19
 */
public class Lock03 {
    // 可设置是否为公平锁，默认false不公平
    // 公平锁是指，在线程等待临界资源时，不能直接去抢占资源，而需要先查看等待队列，如果队列中有其他线程，则需要排队等待。
    // 非公平锁是指，线程在等待临界资源时，可随时抢占，因此会出现后到的线程比先到的线程更早获得资源
    Lock lock = new ReentrantLock(true);

    public Integer count = 0;

    public void m1() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            System.out.println("t1");
            lock.unlock();
        }
    }

    public void m2() {
        for (int i = 0; i < 10; i++) {
            lock.lock();
            System.out.println("t2");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Lock03 lock03 = new Lock03();
        Thread t1 = new Thread(lock03::m1);
        Thread t2 = new Thread(lock03::m2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
