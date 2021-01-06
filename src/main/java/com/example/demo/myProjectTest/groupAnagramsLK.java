package com.example.demo.myProjectTest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/29
 * @time 16:27
 */
public class groupAnagramsLK {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> list = new ArrayList<>();
        Map<String, List<String>> collect = Arrays.stream(strs).collect(Collectors.groupingBy(s -> {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }));
        list = new ArrayList<>(collect.values());
        System.out.println(list);
    }
}
