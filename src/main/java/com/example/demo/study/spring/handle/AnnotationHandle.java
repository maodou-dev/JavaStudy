package com.example.demo.study.spring.handle;

import com.example.demo.study.spring.annotation.MyAnnotation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/5/11
 * @time 14:30
 */
public class AnnotationHandle {

    public static void outPut(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0) {
            Arrays.stream(fields).forEach(field -> {
                MyAnnotation myAnnotation = field.getAnnotation(MyAnnotation.class);
                if (Optional.ofNullable(myAnnotation).isPresent()) {
                    System.out.println("field1: " + myAnnotation.field_id() + " field2: " + myAnnotation.field_name());
                }
            });
        }
    }
}
