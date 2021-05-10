package com.example.demo.collectionLearn;

import java.util.*;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/29
 * @time 11:26
 */
public class ListLearn {
    Iterable iterable;
    Iterator iterator;
    Collection collection; // 继承Iterable 依赖Iterator
    Map map; // 依赖Collection

    ListIterator listIterator; // 继承Iterator
    List list; //继承Collection
    Set set; //继承Collection
    Queue queue; //继承Collection
    AbstractCollection abstractCollection; // 实现Collection
    AbstractList abstractList;
    ArrayList arrayList;

    public void test1() {
        collection = list;
        iterator = collection.iterator();
    }

    public void test2() {
        List list = new Vector(2);
        list.add(1);
        list.add(2);
        list.add(3);
        list.contains(1);
    }

    public void test3(){
        List list1 = new ArrayList();
        List list = new LinkedList(list1);
    }

    public static void main(String[] args) {
//        // ArrayList不指定大小时，初始化大小为10
//        List list2 = new ArrayList();
//        list2.add(1);
//        list2.add(2);
//        list2.add(3);
//        list2.add(4);
//        list2.add(5);
//        list2.add(6);
//        list2.add(7);
//        list2.add(8);
//        list2.add(9);
//        list2.add(10);
//        // 每次扩容 oldSize+(oldSize >> 1)
//        list2.add(11);
//        list2.add(12);


//        // Vector也是底层为数组实现，和ArrayList相同，扩容为一倍，默认大小10，线程安全，使用了synchronized
//        List list3 = new Vector(2);
//        list3.add(1);
//        list3.add(2);
//        list3.add(3);


        // LinkedList线程不安全，没有默认大小，均为0，记录first和last节点，每次新增直接在last节点后加一位即可
        List list4 = new LinkedList();
        list4.add(1);
        list4.add(2);

        // 删除时，LinkedList通过for循环遍历查询节点，进行删除，以下为两种方式，1、通过index删除；2、通过对象删除。
        list4.remove(1);
        list4.remove((Integer)1);
    }


}
