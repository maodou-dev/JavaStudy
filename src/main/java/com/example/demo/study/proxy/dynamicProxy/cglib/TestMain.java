package com.example.demo.study.proxy.dynamicProxy.cglib;

/**
 * CGlib动态代理测试类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 19:02
 */
public class TestMain {
    public static void main(String[] args) {
        CGlibProxy cGlibProxy = new CGlibProxy();
        Boy boy = (Boy) cGlibProxy.getInstance(new Boy());
        boy.setName("小郑");
        boy.eat();
        boy.speak();

    }
}
