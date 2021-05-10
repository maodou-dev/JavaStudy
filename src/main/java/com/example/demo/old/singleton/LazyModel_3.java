package com.example.demo.old.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-29
 * @time 11:09
 */
public class LazyModel_3 {

    // 这里的volatile到底需不需要，先说结论，需要！
    // 为什么？因为volatile还有个作用是保证有序性，也就是禁止指令重排
    // 哪里会重排？在new的过程中有可能重排，这个只是在理论中存在，我复现不了
    // 重排的原因？new的语句，不是原子性操作，也就是说new的这句话，编译成字节码后会有5条指令
    // 1.new（申请内存空间，并且初始化为0或空）;2.忘了指令名，作用是复制指针;3.invokeSpecial 执行构造函数; 4建立局部变量的指定;5.return
    // 当发生指令重排的时候，3-4交换顺序，那么第二个线程到的时候lazyModel_3不为空，但是他的成员变量为空，从而导致问题
    public static volatile LazyModel_3 lazyModel_3 = null;

    public int a = 0;

    public static LazyModel_3 getInstance() { // DCL double check lock
        // DCL就可以完美解决这个问题，承接上一个问题，当两个线程同时来，都是null，来争取锁时，
        // 第一个线程new了一个对象，第二个线程进来后，在判断一次，即可。
        // 第一个if的存在与否，并不会影响最终结果，但是依然存在锁粒度过大的问题，
        // 因为不要第一个if的话，那么每一个线程都会去争夺锁，这样会使得效率降低，争夺锁的过程会消耗CPU资源，因此第一个if的作用是提高效率。
        if (lazyModel_3 == null) {
            synchronized (LazyModel_3.class) {
                if (lazyModel_3 == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lazyModel_3 = new LazyModel_3();
                }

            }

        }
        return lazyModel_3;
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i=0; i<100; i++){
            Thread thread = new Thread() {
                @Override
                public void run() {
                    System.out.println(LazyModel_3.getInstance().hashCode());
                    LazyModel_3.getInstance().a++;
                    countDownLatch.countDown();
                }
            };
            thread.start();
        }
        try {
            countDownLatch.await();
            System.out.println(LazyModel_3.getInstance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
