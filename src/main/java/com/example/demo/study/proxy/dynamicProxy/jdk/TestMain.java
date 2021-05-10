package com.example.demo.study.proxy.dynamicProxy.jdk;

/**
 * JDK动态代理测试类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 19:16
 */
public class TestMain {
    public static void main(String[] args) {
        // 必须接口类，不能实现类
        JDKDynamicInterface man = new Man();

        JDKDynamicProxy jdkDynamicProxy = new JDKDynamicProxy(man);
        JDKDynamicInterface proxy = (JDKDynamicInterface) jdkDynamicProxy.getProxy();

        proxy.eat();
    }
}
