package com.example.demo.myProjectTest;

import java.util.HashSet;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/24
 * @time 15:21
 */
public class MyMain {

    public static void main(String[] args) {
        String arg1 = "test";
        String arg2 = new String("test");
        String arg3 = new String(arg1);
        String arg4 = "test";
        System.out.println(arg1.equals(arg2));
        System.out.println(arg2 == arg3);
        System.out.println(arg1 == arg4);
        HashSet<String> set = new HashSet();
        set.add(arg1);
        set.add(arg2);
        set.add(arg3);
        for (String string : set) {
            System.out.println(string);
        }
    }
}
