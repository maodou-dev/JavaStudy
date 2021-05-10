package com.example.demo.study.proxy.staticProxy;

/**
 * JDK静态代理测试类
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/11
 * @time 18:49
 */
public class TestMain {

    public static void main(String[] args) {
        Girl girl = new Girl();
        girl.setName("小张");
        GirlProxy01 girlProxy01 = new GirlProxy01(girl);
        girlProxy01.eat();
    }
}
