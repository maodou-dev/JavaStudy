package com.example.demo.study.proxy.dynamicProxy.cglib;

/**
 * CGlib动态代理，被代理类
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 18:53
 */
public class Boy extends Man {

    private String name;

    public Boy() {
        super();
    }

    public Boy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        super.name = name;
    }

    public void eat(){
        System.out.println(name + " -> 被代理类 -> 吃饭");
    }
}
