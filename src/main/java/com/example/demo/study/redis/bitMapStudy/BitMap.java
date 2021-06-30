package com.example.demo.study.redis.bitMapStudy;

/**
 * 基于study01的学习，我们来封装一下BitMap，其中的详细注释在study01，这里不做详细描述
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/6/22
 * @time 11:11
 */
public class BitMap {

    /**
     * 该BitMap的容量，即最大可以存储的数字
     */
    private int capacity;

    /**
     * 用户存储的空间
     */
    private byte[] bits;

    public BitMap(int capacity) {
        this.capacity = capacity;
        // this.bits = new byte[(capacity/8) +1]; // 第一种写法
        this.bits = new byte[(capacity >> 2) + 1]; // 第二种写法
    }

    /**
     * 添加数字
     *
     * @param num
     */
    public void addNum(int num) {
        int index = num >> 2; // 除以8
        int pos = num % 8;
        bits[index] |= (1 << pos);
    }

    /**
     * 查看数字是否存在
     *
     * @param num
     * @return
     */
    public boolean findNum(int num) {
        int index = num >> 2; // 除以8
        int pos = num % 8;
        return (bits[index] & (1 << pos)) != 0;
    }

    /**
     * 删除数字
     *
     * @param num
     */
    public void deleteNum(int num) {
        int index = num >> 2; // 除以8
        int pos = num % 8;
        bits[index] &= ~(1 << pos);
    }

}
