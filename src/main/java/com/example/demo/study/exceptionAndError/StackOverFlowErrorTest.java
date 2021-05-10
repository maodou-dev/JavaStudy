package com.example.demo.study.exceptionAndError;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java.lang.StackOverflowError
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/4/14
 * @time 15:12
 */
public class StackOverFlowErrorTest {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static final long MAX = 100_000L;
    public static void Foo(){
        if (atomicInteger.get() < MAX) {
            atomicInteger.incrementAndGet();
            Foo();
        } else {
            System.out.println("over");
        }

    }

    public static void main(String[] args) {
        Foo();
    }

}
