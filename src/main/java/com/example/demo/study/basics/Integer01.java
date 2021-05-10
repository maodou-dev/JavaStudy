package com.example.demo.study.basics;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/12
 * @time 9:41
 */
public class Integer01 {
    public static void main(String[] args) {
        // 正常Integer的范围是-127~128
        // -XX:AutoBoxCacheMax=129
        // AutoBoxCacheMax这个参数可以配置Integer的常量池大小，JVM调优
        // Integer最大值最小为127，如果配置了比127还小的值，则为127
        Integer a = 127;
        Integer b = 127;
        // 所以包装类型间的相等判断应该用equals，而不是'=='
        System.out.println(a == b); // false
        System.out.println(a.equals(b)); // true

    }
}
