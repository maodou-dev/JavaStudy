package com.example.demo.projectTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2020/12/29
 * @time 15:06
 */
@Component
public class Teacher {
    @Autowired
    Student student;

    String name = "teacher";

    public Teacher() {
        System.out.println(student + "11111");
    }

}
