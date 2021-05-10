package com.example.demo.old.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-30
 * @time 15:00
 */
public class CAS {
    public static AtomicLong atomicLong = new AtomicLong(0L);

    public static void main(String[] args) throws Exception {
//        atomicLong.incrementAndGet();

        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);

        for (int i = 0; i<threads.length;i++){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        for (int j = 0; j < 100; j++) {
                            atomicLong.incrementAndGet();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    countDownLatch.countDown();
                }
            };
            threads[i] = thread;
        }

        for (int i = 0; i<threads.length;i++) {
            threads[i].start();
        }

        countDownLatch.await();
        System.out.println(atomicLong.get());

    }
}
