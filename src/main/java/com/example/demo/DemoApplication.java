package com.example.demo;

import com.example.demo.projectTest.Student;
import com.example.demo.projectTest.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext c = SpringApplication.run(DemoApplication.class, args);
        Teacher teacher = c.getBean(Teacher.class);
        Student student = c.getBean(Student.class);
        System.out.println(teacher);
    }

}
