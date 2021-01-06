package com.example.demo.myTest;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/4
 * @time 15:17
 */
public class Test01 {
    public static abstract class Man {
        public Man(){
            print();
        }
        public abstract void print();
    }

    public static class Student extends Man{

        public Student(){
            super();
        }

        @Override
        public void print() {
            System.out.println("Student");
        }
    }

    public static void main(String[] args) {
        new Student();
    }
}
