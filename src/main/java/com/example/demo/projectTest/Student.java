package com.example.demo.projectTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
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
@DependsOn({"teacher"})
public class Student {

//    @Autowired
    Teacher teacher;
    String name = "student";

    public Student(Teacher teacher1) {
        this.teacher = teacher1;
        System.out.println(teacher);
    }
}
