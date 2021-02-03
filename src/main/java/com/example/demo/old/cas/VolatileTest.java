package com.example.demo.old.cas;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-30
 * @time 15:07
 */
public class VolatileTest {

    public static int a,b = 0;
    public static int x,y = 0;


    public static void main(String[] args) throws Exception {

        int i=0;
        while(true) {
            i++;
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            };

            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            };

            thread1.start();thread2.start();
            thread1.join();thread2.join();
            if (x == 0 && y==0) {
                System.err.println("第"+i+"次，x="+x+",y="+y);
                break;
            }

        }

    }
}
