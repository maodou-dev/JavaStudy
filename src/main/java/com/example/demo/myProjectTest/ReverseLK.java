package com.example.demo.myProjectTest;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/24
 * @time 17:00
 */
public class ReverseLK {
    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * <p>
     * 输入: 120
     * 输出: 21
     * <p>
     * 注意:
     * <p>
     * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param args
     */
    public static void main(String[] args) {
        int x = 1534236469;
        int num;
        StringBuffer sb = new StringBuffer();
        if (x < 0) {
            sb.append("-");
            x = -x;
        }
        String xStr = String.valueOf(x);
        for (int i = xStr.length()-1; i >= 0; i--) {
            sb.append(xStr.charAt(i));
        }
        try{
            num = Integer.valueOf(sb.toString());
        } catch (Exception e) {
            num = 0;
        }
        System.out.println(num);
    }


}
