package com.example.demo.study.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/5
 * @time 19:03
 */
public class DCL01 {
    // DCL(double check lock) 双重检查
    // 目的：保证单例，并且尽可能减少锁的力度，尽量细一点
    public static Object object;

    private DCL01() {
    }

    public static Object getInstance() {

        if (object == null) {
            // synchronized 目前是先从： 偏向锁 -> 自旋锁 -> 重量级锁(OS)
            // 当自旋次数达10次以上，或者线程过多时，则转为重量级锁
            // 重量级锁不占用CPU资源，但是速度很慢
            synchronized (DCL01.class) {
                if (object == null) {
                    object = new Object();
                }
            }

        }
        return object;
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> {
                System.out.println(DCL01.getInstance());
            }));
        }
        for (Thread t : threads) {
            t.start();
        }
    }
}
