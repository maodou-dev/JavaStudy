package com.example.demo.mybatis.entity;

import java.io.Serializable;

/**
 * User对象
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/6/1
 * @time 19:39
 */
public class User implements Serializable {

//    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
