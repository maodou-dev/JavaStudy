package com.example.demo.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/5
 * @time 15:34
 */
public class Volatile02 {
    // 不要加static
    // volatile具有可见性，不具有原子性
    public /*volatile*/ boolean flag = true;

    public void m() {
        System.out.println(Thread.currentThread().getName() + " start");

        while (flag) {
            int x = 1;
            /**
             * synchronized具有原子性，可见性，有序性
             * 因此在循环中使用了synchronized的话，也不会死循环
             */
            synchronized (this) {
                x++;
            }

            //System.out.println(x);
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }


    public static void main(String[] args) throws InterruptedException {
        Volatile02 volatile02 = new Volatile02();

        new Thread(volatile02::m).start();

        Thread.sleep(1000);
        volatile02.flag = false;

        boolean a = true;
        String m = a==false?(new Test2().getName()):"";
        System.out.println(m);

    }

    static class Test2{
        String name;

        public String getName() {
            name = "a";
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
