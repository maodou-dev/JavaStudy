package com.example.demo.myProjectTest;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/22
 * @time 18:44
 */
public class FooLK {

    public FooLK() {

    }
    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
