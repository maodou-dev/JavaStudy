package com.example.demo.study.basics;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/7
 * @time 16:21
 */
public class String01 {
    public static void main(String[] args) {
        // JVM中只有是""双引号引起来的，那么就一定会放入常量池
        // 此时会将abc放入常量池，但是此时常量池中的对象引用并不是指向的b，因为new String的时候会创建两个对象，一个放在常量池，一个在堆中，堆中的才指向b
        // 所以此时的 b.intern() == b 返回false
        //String b = new StringBuffer("abc").toString();
        // 此时放入常量池中的是"abcd",而且这不是指向的c
//        String c = new StringBuffer("abc").append("d").toString();
//        String b = "abcd";
//        System.out.println(c.intern() == b);
        // 此时会在常量池中创建"abcd"的对象，也会在堆中创建一个对象，但是常量池中的对象不是指向的d，所以此时的d.intern() == d 返回false
        //String d = new String("abcd");
        //String m = "abcd";
        // d创建时，
        // intern方法会把b放入常量池，如果常量池中本来就有"abcd"那么就直接返回常量池中的对象引用，如果没有那就创建一个，再返回引用对象，此时的引用对象就是b
        //b.intern();
        String a = new String("1") + new String("1");

        a.intern();
        String b = new String("11").intern();
        System.out.println(a == b);
//        System.out.println(d.intern() == d);
//        System.out.println(c.intern()==c);
//        System.out.println(b.intern()==c.intern());
//        System.out.println(c.intern()==b);
//        System.out.println(b.intern()==b);
    }
}
