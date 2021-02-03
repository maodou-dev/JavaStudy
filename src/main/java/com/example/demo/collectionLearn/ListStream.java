package com.example.demo.collectionLearn;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/15
 * @time 14:33
 */
public class ListStream {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList();
        list1.add(4);
        list1.add(2);
        list1.add(3);
        list1.add(1);
        List<Integer> collect = list1.stream().map((key) -> {
            Map<String, Object> map = new HashMap<>();
            map.put(key.toString(),"xx");
            return key;
        }).sorted().collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(list1);
    }
}
