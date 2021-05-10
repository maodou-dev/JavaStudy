package com.example.demo.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/7
 * @time 17:55
 */
public class ReadWriteLock01 {
    // 读写锁，读锁也就是共享锁，写锁也就是排他锁，synchronized也是排他锁
    Lock lock = new ReentrantLock();
    ReentrantReadWriteLock lock1 = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock1.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock1.writeLock();

    private Integer value = 0;

    public void read() {
        try {
            readLock.lock();
            // value = 2;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("read");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            TimeUnit.SECONDS.sleep(2);
            value = 2;
            System.out.println("write");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock01 lock = new ReadWriteLock01();
        List<Thread> readThreads = new ArrayList<>();
        List<Thread> writeThreads = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            readThreads.add(new Thread(lock::read));
        }
        for (int i = 0; i < 2; i++) {
            writeThreads.add(new Thread(lock::write));
        }

        for (Thread t :
                readThreads) {
            t.start();
        }
        for (Thread t :
                writeThreads) {
            t.start();
        }
        for (Thread t :
                readThreads) {
            t.join();
        }
        for (Thread t :
                writeThreads) {
            t.join();
        }

    }

}
