package com.example.demo.study.redis.bloomFilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器学习
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/6/23
 * @time 14:59
 */
public class BloomFilterStudy {

    /**
     * 容量
     */
    private static final Integer SIZE = 1000000;
    /**
     * 错误率
     */
    private static final double FPP = 0.01;

    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(
                Funnels.integerFunnel(),
                SIZE, // 容量
                FPP); // 错误率

        /*// 加入元素
        bloomFilter.put(1);
        // 查询过滤器中是否存在某个元素
        System.out.println(bloomFilter.mightContain(1));*/
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
    }
}
