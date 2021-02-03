package com.example.demo.old.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-11-07
 * @time 13:35
 */
@Controller
@RequestMapping("demo")
public class TestController {


    @RequestMapping("/test")
    @ResponseBody
    public String test(String name){
        return name+"post";
    }

    @RequestMapping("/getTest")
    @ResponseBody
    public String getTest(String name){
        return name+"get";
    }
}
