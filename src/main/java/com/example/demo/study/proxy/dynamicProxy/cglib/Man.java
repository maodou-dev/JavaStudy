package com.example.demo.study.proxy.dynamicProxy.cglib;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/4/14
 * @time 14:43
 */
public class Man {
    protected String name;
    void speak() {
        System.out.println(this.name + " speak");
    }
}
