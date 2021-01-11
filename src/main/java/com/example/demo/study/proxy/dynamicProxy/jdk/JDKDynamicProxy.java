package com.example.demo.study.proxy.dynamicProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理，代理类
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 19:11
 */
public class JDKDynamicProxy implements InvocationHandler {
    /**
     * 被代理类
     */
    private Object target;

    public JDKDynamicProxy() {
    }

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     * @return
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass()
                .getInterfaces(), this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理前");
        Object obj = method.invoke(target,args);
        System.out.println("JDK动态代理后");
        return obj;
    }
}
