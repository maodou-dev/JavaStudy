package com.example.demo.study.proxy.staticProxy;

/**
 * 被代理类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 17:43
 */
public class Girl implements ProxyInterface {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println(name + "->被代理类->吃饭");
    }
}
