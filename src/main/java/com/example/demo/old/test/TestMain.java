package com.example.demo.old.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-17
 * @time 9:43
 */
public class TestMain {


    public static void main(String[] args) {
//        String smsTmplCont = "【贵州医保网厅】【国家医保局】您正在重置单位网厅账号的密码，验证码564708，10分钟内有效，请勿泄露。";
//        System.out.println(smsTmplCont.replaceAll( "【贵州医保网厅】",""));
        String str = "[{'a':'1'},{'b':'2'}]";
        Object parse = JSONObject.parse(str);
        List<Map> objects = JSONObject.parseArray(str, Map.class);
        System.out.println(parse);

    }
}
