package com.example.demo.study.basics;

import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/6/29
 * @time 19:48
 */
public class Url01 {

    public static void main(String[] args) {
        URI uri = UriComponentsBuilder.fromUriString("http://www.baidu.com").build().toUri();
        System.out.println(uri);
        uri = UriComponentsBuilder.fromUriString("http://www.baidu.com").queryParam("name", "abc").queryParam("code", "123").build().toUri();
        System.out.println(uri);
        Date date = new Date();
        uri = UriComponentsBuilder.fromUriString("http://www.baidu.com/{a}/{b}").build(1,date);
        System.out.println(uri);

    }
}
