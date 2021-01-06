package com.example.demo.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/5
 * @time 17:48
 */
public class CAS01 {
    // CAS(Compare And Swap) 比较和交换
    // 自旋锁———— AtomicXXX
    // 可能会出现ABA问题，解决方法是加version版本号
    AtomicInteger atomicInteger = new AtomicInteger();

    public void m() {
        for (int i = 0; i < 10000; i++) {
            atomicInteger.incrementAndGet();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        CAS01 cas01 = new CAS01();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(cas01::m));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(cas01.atomicInteger);

    }
}
