package com.example.demo.jvm;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/5/8
 * @time 10:12
 */
public class gcTest {

    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
            // gc放在这里不会执行
//        System.gc();
        }
        // 这样写也不会执行
//        System.gc();

        // 这样写会执行 原因是局部变量表的slot 被int a占用了，所以placeholder就没有什么关联，即可gc
        int a = 0;
        System.gc();

    }
}
