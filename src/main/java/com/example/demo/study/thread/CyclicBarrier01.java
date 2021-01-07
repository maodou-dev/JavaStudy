package com.example.demo.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/7
 * @time 14:47
 */
public class CyclicBarrier01 {
    // CyclicBarrier也是一个等待线程执行工具，当线程数量达到时，就可继续执行
    // 它拥有两个构造函数，如下：
    // parties：代表需要达到的线程数量
    // Runnable：代表到达数量后执行的代码
    CyclicBarrier cyclicBarrier1 = new CyclicBarrier(10);
    CyclicBarrier cyclicBarrier2 = new CyclicBarrier(10, () -> System.out.println("OK"));

    public static void main(String[] args) {
        CyclicBarrier01 barrier = new CyclicBarrier01();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            threads.add(new Thread(() -> {
                try {
                    barrier.cyclicBarrier2.await();
                    //System.out.println(Thread.currentThread().getName());
                    System.out.println(barrier.cyclicBarrier2.getNumberWaiting());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
