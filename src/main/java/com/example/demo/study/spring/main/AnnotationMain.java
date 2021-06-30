package com.example.demo.study.spring.main;

import com.example.demo.study.spring.annotation.MyAnnotation;
import com.example.demo.study.spring.entity.AnnotationEntity;
import com.example.demo.study.spring.handle.AnnotationHandle;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/5/11
 * @time 14:37
 */
public class AnnotationMain {

    @MyAnnotation(field_id = "id", field_name = "name")
    private static AnnotationEntity annotationEntity;
    public static void main(String[] args) {

        AnnotationHandle.outPut(AnnotationMain.class);
    }
}
