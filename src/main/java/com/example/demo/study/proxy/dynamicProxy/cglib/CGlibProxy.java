package com.example.demo.study.proxy.dynamicProxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGlib动态代理类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 18:56
 */
public class CGlibProxy implements MethodInterceptor {
    /**
     * 被代理类
     */
    private Object target;

    /**
     * 创建代理实例
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        // 实例化增强器
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGlib动态代理调用方法前");
        Object obj = method.invoke(this.target, objects);
        System.out.println("CGlib动态代理调用方法后");
        return obj;
    }
}
