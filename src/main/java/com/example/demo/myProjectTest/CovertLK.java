package com.example.demo.myProjectTest;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/24
 * @time 15:47
 */
public class CovertLK {

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * <p>
     * 1   7
     * 2  68
     * 3 5 9
     * 4   10
     * <p>
     * 1    9
     * 2   8
     * 3  7
     * 4 6
     * 5
     * 请你实现这个将字符串进行指定行数变换的函数：
     * 0 1 3 5 7
     * an = a1 + (n-1)d
     * an = 1 + (numRows - 2)2
     * string convert(string s, int numRows);
     * 示例 1:
     * <p>
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     *
     * @param args
     */
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 4;
//        String resultStr = "";
//        if (numRows == 1) {
//            resultStr = s;
//        }
//        // 等差数列的公差d
//        int d1;
//        int d2;
//        StringBuffer sb = new StringBuffer();
//        int index;
//        int time;
//        for (int i = 0; i < numRows; i++) {
//            if (i == numRows - 1) {
//                d1 = 0;
//                d2 = (numRows - 2) * 2 + 2;
//            } else {
//                d1 = (numRows - 2 - i) * 2 + 2;
//                d2 = (i - 1) * 2 + 2;
//            }
//            if (i == 0) {
//                d2 = 0;
//            }
//            index = i;
//            time = 0;
//            while (index < s.length()) {
//                sb.append(s.charAt(index));
//                if (d1 == 0) {
//                    index += d2;
//                } else if (d2 == 0) {
//                    index += d1;
//                } else {
//                    if (time % 2 == 0) {
//                        index += d1;
//                    } else {
//                        index += d2;
//                    }
//                }
//                time++;
//
//            }
//        }
//        System.out.println(sb.toString());
        convert(s,numRows);
    }

    public static String convert(String s, int numRows) {
        if(numRows < 2)return s;
        char[] ch = s.toCharArray();
        //初始化，用Arrays.fill会出问题
        StringBuilder[] rowString = new StringBuilder[numRows];//n行
        for(int i = 0; i < numRows; i++){
            rowString[i] = new StringBuilder();
        }
        //开始找规律的计算
        int n = numRows * 2 - 2;
        for(int i = 0; i < ch.length; i++){
            int pos = i % n;
            if(pos < numRows){
                rowString[pos].append(ch[i]);
            }else{
                rowString[n-pos].append(ch[i]);
            }
        }
        StringBuilder ans = new StringBuilder();
        for(StringBuilder sb: rowString) ans.append(sb);
        return ans.toString();
    }
}
