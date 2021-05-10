package com.example.demo.old.singleton;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-29
 * @time 11:05
 */
public class HungryModel {
    // 饿汉模式，即程序初始化就new出来一个对象，每次使用都返回这个对象
    public static HungryModel hungryModel = new HungryModel();

    public static HungryModel getInstance(){
        return hungryModel;
    }

    public static void main(String[] args) {
        HungryModel hungryModel1 = HungryModel.getInstance();
        HungryModel hungryModel2 = HungryModel.getInstance();
        System.out.println(hungryModel1 == hungryModel2); // 一定是true
    }
}
