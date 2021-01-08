package com.example.demo.study.spring;

import com.example.demo.study.spring.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean的自动装配
 *
 * @author zhengyd
 * @version 1.0.0
 * @mail zhengyd@yinhai.com
 * @date 2021/1/8
 * @time 14:48
 */
@Configuration
public class MyConfiguration {

    @Bean("person")
    public Person getPerson() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(19);
        return person;
    }
}
