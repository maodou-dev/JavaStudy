package com.example.demo.study.thread;

import org.openjdk.jol.info.ClassLayout;
import org.springframework.data.redis.core.TimeoutUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/21
 * @time 20:52
 */
public class Synchronized01 {

    static Object o = new Object();
    public static void m1() {
        synchronized (o) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void m2() {

        m1();
    }

    public static void main(String[] args) throws InterruptedException {
        Synchronized01 s = new Synchronized01();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Thread t1 = new Thread(s::m2);
        Thread t2 = new Thread(s::m2);
        t1.start();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        t2.start();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        t1.join();
        t2.join();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
