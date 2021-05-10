package com.example.demo.study.spring.entity;

import org.springframework.stereotype.Component;

/**
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/8
 * @time 20:42
 */
@Component
public class Food {
    private String name;

    public Food() {
        name = "香蕉";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
