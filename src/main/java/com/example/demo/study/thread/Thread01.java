package com.example.demo.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/4
 * @time 15:37
 */
public class Thread01 {
    private static class ExtendThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    private static class ImplementsRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    private static class ExtendFutureTask extends FutureTask {
//        public ExtendFutureTask(Callable callable) {
//            super(callable);
//        }

        public ExtendFutureTask(Runnable runnable, Object result) {
            super(runnable, result);
        }
    }

    private static class ImplementsCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            return "aaa";
        }
    }

    public static void main(String[] args) {
//        new ExtendThread().start();
//        ImplementsRunnable implementsRunnable = new ImplementsRunnable();
//        new Thread(implementsRunnable).start();
//        Callable callable = new Callable() {
//            @Override
//            public Object call() throws Exception {
//                return 1;
//            }
//        };
////        FutureTask<Integer> futureTask = new ExtendFutureTask(callable);
//        FutureTask<Integer> futureTask = new ExtendFutureTask(implementsRunnable, 2);
//        new Thread(futureTask).start();
//        try {
//            System.out.println(futureTask.get());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        ImplementsCallable callable1 = new ImplementsCallable();
        FutureTask futureTask1 = new FutureTask(callable1);
        new Thread(futureTask1).start();
        try {
            System.out.println(futureTask1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
