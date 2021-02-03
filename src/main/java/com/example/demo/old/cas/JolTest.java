package com.example.demo.old.cas;


import org.openjdk.jol.info.ClassLayout;

/**
 * 通过ClassLayout来查看对象的变化
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-30
 * @time 15:12
 */
public class JolTest {

    /**
     * OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
     * 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
     * 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
     * 8     4        (object header)                           43 c1 00 20 (01000011 11000001 00000000 00100000) (536920387)
     * 12     4    int TestObj.a                                 0
     * 16     4    int TestObj.b                                 0
     * 20     4        (loss due to the next object alignment)
     * Instance size: 24 bytes
     * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
     * <p>
     * 可以看出，我们在new一个对象的时候，申请的内存空间有4个部分
     * 1. 0-8 8个字节代表header的 Mark Word（标记）如果对该对象加了锁，那么这个就会在Mark Word中写入信息
     * 2. 8-12 4个字节代表了该对象属于哪一个类，该实例中就代表TestObj这个类
     * 3. 12-n 存储成员变量
     * 4. n-m 因为一个对象总共占据的字节数要为4的倍数，所以这里是一个补齐操作
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Thread.sleep(4500); // 大概在JVM启动后，差不多4点几秒后，开启偏向锁，偏向锁相当于无锁，只是写入标记，第二个线程到来时，升级成轻量级锁（自旋锁）
        TestObj testObj = new TestObj();
        System.out.println(ClassLayout.parseInstance(testObj).toPrintable());
//        ClassLayout.parseClass(TestObj.class);

        // 在jdk 1.0-1.2时，synchronized是一把重量级锁，直接需要OS的调度
        // 在1.4过后可以锁升级，但是需要自己配置
        // 在1.6过后，不需要自己配置，synchronized自动实现锁升级
        // 锁升级过程是，先判断程序是否开启了偏向锁，如果未开启，那么直接变成轻量级锁（自旋锁），如果已开启，从匿名偏向变为指定偏向锁
        // 当第二线程来时，偏向锁变为轻量级锁（自旋锁）
        // 当有线程超过10次自旋， 或者自旋线程数超过CPU核数的一半，升级为重量级锁，所有需要锁却没有获得锁的线程，不在自旋，不占用CPU资源，直接等待OS的调度
        // 所以由此也可以看出，重量级锁的效率不一定比轻量级锁效率低，如果自旋超过10次或者，自旋线程数超过CPU核数的一半时，重量级更好，虽然慢，但是不浪费CPU资源
        // 再提一下自旋锁的原理，自旋锁是不跟OS打交道，是自旋线程一直在死循环，等待锁的释放。自旋锁也是乐观锁，不是主动加锁，只有在最后比较、写入的过程中会加锁
        // 是cpp代码执行的指令 lock cmpxchg 对cmpxchg这个操作加锁，保证原子性，因为加锁是用的CPU指令，所以比起重量级锁直接需要OS的控制来说，效率高很多
        synchronized (testObj) {
            System.out.println(ClassLayout.parseInstance(testObj).toPrintable());
        }

        //System.out.println(ClassLayout.parseInstance(testObj).toPrintable());
    }
}
