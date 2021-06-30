package com.example.demo.study.spring.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/9
 * @time 16:05
 */
//@Component
public class B {


//    @Autowired
    private A a;

    private String name = "B";
    public B(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    //    public C getC() {
//        return c;
//    }
//
//    public void setC(C c) {
//        this.c = c;
//    }
}
