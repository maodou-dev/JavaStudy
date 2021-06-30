package com.example.demo.study.redis.bitMapStudy;

/**
 * bitMap学习1
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/6/22
 * @time 9:29
 */
public class Study01 {
    /**
     * bitMap的基本思想是用一个bit位来标记某个元素对应的value，用这个元素作为key；
     * 使用bit来作为存储是非常节省空间的，bit是信息的最小单位；
     * 每个bit中都只含一个值：0或1；0代表不存在，1代表存在；
     * 那么如何来表示一个数呢？
     * 计算机中内存分配的最小单位为字节(byte)即8个bit，因此一个字节只能存储8个数；
     * 如果表示0-7，即一个字节就可以表示，但如果表示更大的数字，则需要用更多的字节；
     * 现在我们用int来表示，一个int有4个字节，即32个bit，所以一个int能表示32个数字；
     * 那么我们现在来申请一个int数组来存放我们的数据：int temp[len]，那么len应该是多大呢？
     * 如果我们需要存0-31这些数字，那么len = 1；如果需要存0-63，那么len = 2；如果我们需要0-64，那么len = 3；
     * 由此可见，len应该满足1+N/32，N为存放的最大值；即，如果需要存放0-100，那么len = 1 + 100/32 = 4；
     * 所以当我们要查找某一个值M时，M/32即是数组下标，M%32即是在此下标的位置；
     * 但是用int的32为时，容易数据过大，(2的32次方)，所以这里用byte数组；
     * byte数组是同理，只是把32改成8即可。
     */
    /**
     * 定义N的大小
     */
    private static final int N = 100;

    public static void main(String[] args) {

        // 现在我们初始化一个我们的BitMap，里面没有一个数字，即所有bit都为0
        byte[] myBitMap = new byte[1 + N / 8];

        /**
         * 现在我们来添加数字
         * 如何在BitMap中添加一个数字呢？
         * 假设说：我们想要把7放入myBitMap中，就应该放在myBitMap[0]的第8个bit中，如下：
         * 源myBitMap[0]:            0 0 0 0 0 0 0 0
         * 把1左移7位，放置第8个位置：   1 0 0 0 0 0 0 0 (1初始在右边第一个位置，左移7位到现在这个位置)
         * 将上下想或得：              1 0 0 0 0 0 0 0
         * 由此可见，我们想要添加一个数字时，只需要myBitMap[0] | 1 << 7即可；
         * 现在添加5,46,88到我们的myBitMap
         */
        myBitMap[5 / 8] |= 1 << 5 % 8;
        myBitMap[46 / 8] |= 1 << 46 % 8;
        myBitMap[88 / 8] |= 1 << 88 % 8;


        /**
         * 我们刚刚添加了三个数字，那么我们如果查找他们是否存在呢？
         * 按照刚刚的逻辑，我们添加数字后，那么在myBitMap[index]中的相应位置一定为1；
         * 所以我们可以将需要查找的数字，去用且看看是否为1；
         * 我们现在查找5,46,88,77，按照逻辑，77应该是为0的；如果不为0则表示存在
         */
        System.out.println(5 % 8);
        System.out.println(46 % 8);
        System.out.println(88 % 8);
        System.out.println(myBitMap[5 / 8] & 1 << 5 % 8);
        System.out.println(myBitMap[46 / 8] & 1 << 46 % 8);
        System.out.println(myBitMap[88 / 8] & 1 << 88 % 8);
        System.out.println(myBitMap[77 / 8] & 1 << 77 % 8);


        /**
         * 现在我们再来删除数字；如何删除呢？
         * 一样的逻辑，我们只需要把对应为1的值，修改为0即可；如何修改呢？
         * 很简单，我们依然用1左移，但是移动后取反在且即可；
         * 现在我们删除5
         */
        myBitMap[5 / 8] &= ~(1 << 5 % 8);
        System.out.println(myBitMap[5 / 8] & 1 << 5 % 8);


        /**
         * 在不采用第三个参数，实现两个值互换
         * a = 5, b = 3
         * 方法1:加减乘除法
         * a = a + b = 8
         * b = a - b = 5
         * a = a - b = 3
         *
         * a = a * b = 24
         * b = a / b = 8
         * a = a / b = 3
         * 方法2：异或法
         * 异或（1 ^ 1 = 0, 1 ^ 0 = 1, 0 ^ 0 = 0, 0 ^ 1 = 1）
         * 1 0 1  5     a
         * 0 1 1  3     b
         * 1 1 0  6     a = a ^ b = 6
         * 1 0 1  5     b = a ^ b = 5
         * 0 1 1  3     a = a ^ b = 3
         */
        int a = 48;
        int b = 32;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }
}
