package com.example.demo.study.proxy.dynamicProxy.jdk;

/**
 * jdk动态代理，被代理类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 19:09
 */
public class Man implements JDKDynamicInterface {

    @Override
    public void eat() {
        System.out.println("被代理类 -> 吃饭");
    }
}
