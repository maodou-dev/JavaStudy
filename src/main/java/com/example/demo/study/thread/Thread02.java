package com.example.demo.study.thread;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/4
 * @time 16:09
 */
public class Thread02 {

    public static class ThreadOne implements Runnable {

        @Override
        public void run() {
            System.out.println("one-----------start");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("one-----------end");
        }
    }

    public static class ThreadTwo implements Runnable {
        static Thread T1;

        @Override
        public void run() {
            // join()方法可以控制线程执行顺序
            // join()方法的本质是将自己加入到其他线程中，等待其他线程结束后，再继续执行当前线程
            try {
                T1.join();
                System.out.println("two-----------start");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("two-----------end");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ThreadOne());
        Thread t2 = new Thread(new ThreadTwo());
        ThreadTwo.T1 = t1;
        t1.start();
        t2.start();

    }
}
