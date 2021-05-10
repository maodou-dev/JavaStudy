package com.example.demo.collectionLearn;

import java.util.*;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/29
 * @time 14:40
 */
public class SetLearn {

    public void test1() {
        Set set = new HashSet(2);
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(1);
    }

    public void test2() {
        Set set = new TreeSet();
        set.add(1);
    }

    public static void main(String[] args) {
        // HashSet内部使用HashMap实现，实际上就是将add的值，作为map的key值，map的value则为虚拟值，因此无序不可重复
        Set set = new HashSet(2);
        set.add(3);
        set.add(1);
        set.add(2);
        set.add(1);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
