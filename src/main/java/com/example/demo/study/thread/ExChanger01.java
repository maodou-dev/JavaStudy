package com.example.demo.study.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/8
 * @time 20:04
 */
public class ExChanger01 {
    // 进行线程间的数据交互
    // 用于了解
    Exchanger exchanger = new Exchanger();
    String value = "this";

    public void m() {
        String s1 = "m";
        try {
            // 等待exchange，一直到另一个线程exchange
            s1 = exchanger.exchange(s1).toString();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("m" + " " + s1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void n() {
        String s2 = "n";
        try {
            s2 = exchanger.exchange(s2).toString();
            TimeUnit.SECONDS.sleep(5);
            System.out.println("n" + " " + s2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExChanger01 exchanger = new ExChanger01();
        Thread t1 = new Thread(exchanger::m);
        Thread t2 = new Thread(exchanger::n);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
