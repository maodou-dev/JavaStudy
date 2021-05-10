package com.example.demo.myTest;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/4
 * @time 15:17
 */
public class Test01 {
//    public static abstract class Man {
//        public Man(){
//            print();
//        }
//        public abstract void print();
//    }
//
//    public static class Student extends Man{
//
//        public Student(){
//            super();
//        }
//
//        @Override
//        public void print() {
//            System.out.println("Student");
//        }
//    }
static class F{
    int a=1;
    F(){
        this.a = 2;
        System.out.println(1);
    }
}
static class S extends F{
    int a = 3;
    S(){
        super();
        this.a=4;
    }
}

    public static void main(String[] args) {
//        new Student();
        S s = new S();
        System.out.println(s.a);
    }
}
