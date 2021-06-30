package com.example.demo.study.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/5/11
 * @time 14:27
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    public String field_id() default "0";

    public String field_name() default "无";


}
