package com.example.demo.study.proxy.staticProxy;

/**
 * JDK静态代理类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 17:44
 */
public class GirlProxy01 implements ProxyInterface {

    private ProxyInterface proxyInterface;

    public GirlProxy01() {
    }

    public GirlProxy01(ProxyInterface proxyInterface) {
        this.proxyInterface = proxyInterface;
    }

    @Override
    public void eat() {
        System.out.println("在静态代理类中->调用eat方法之前");
        proxyInterface.eat();
        System.out.println("在静态代理类中->调用eat方法之后");
    }
}
