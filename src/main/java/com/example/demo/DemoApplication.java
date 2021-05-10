package com.example.demo;

import com.example.demo.projectTest.Student;
import com.example.demo.projectTest.Teacher;
import com.example.demo.study.spring.entity.A;
import com.example.demo.study.spring.entity.B;
import com.example.demo.study.spring.entity.C;
import com.example.demo.study.spring.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.TimeoutUtils;

import java.util.concurrent.TimeUnit;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext cxt = SpringApplication.run(DemoApplication.class, args);
        A a = cxt.getBean(A.class);
        B b = cxt.getBean(B.class);
//        C c = cxt.getBean(C.class);
        System.out.println();

    }

}
