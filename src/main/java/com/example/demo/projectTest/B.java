package com.example.demo.projectTest;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/1/27
 * @time 19:06
 */
public class B extends A {

    // B继承A，可以直接用B.method()，调用A的静态方法
    public static void main(String[] args) {
        B.print();
    }
}
