package com.example.demo.old.singleton;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-11-06
 * @time 18:04
 */
public class Test {



    public static void main(String[] args) throws Exception {
        URL url = new URL("http://shared.csb.cn-hangzhou.aliyuncs.com:8086/CSB");

        URLConnection conn = url.openConnection();

        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 分配的ak,sk
        String ak = "****";
        String sk = "****";
        // 使用TreeMap完成字典序排序
        TreeMap<String, String> params = new TreeMap<>();

        String timesatemp = String.valueOf(System.currentTimeMillis());
        // 需要放在HTTP header中的key
        List<String> headers = Arrays.asList(new String[]{
                "_api_access_key",
                "_api_name",
                "_api_version",
                "_api_timestamp"
        });
        // 公共传递参数
        params.put("_api_access_key", ak);
        params.put("_api_name", "demoZ");
        params.put("_api_version", "1.0.0");
        params.put("_api_timestamp", timesatemp);
        // 业务数据
        params.put("name", "aa");


        Set<Map.Entry<String, String>> set = params.entrySet();
        // 签名串:key1=value1&key2=value2
        StringBuffer buffer = new StringBuffer();
        // http post数据：key1=Urlencode(value1)
        StringBuffer bodyBuf   = new StringBuffer();

        for(Map.Entry<String, String> e : set){
            String key = e.getKey();
            // 公共数据和业务数据都需要参加签名
            buffer.append('&').append(key).append("=").append(e.getValue());
            if(headers.contains(key)){
                conn.setRequestProperty(key, e.getValue());
            }else{
                // post的业务数据需要URLEncdoe
                bodyBuf.append('&').append(key).append("=").append(URLEncoder.encode(e.getValue(),"utf-8"));
            }
        }

        String str = buffer.substring(1);
        System.out.println("待签名字串："+str);

        Charset charset = Charset.forName("utf-8");
        // 加签
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(sk.getBytes(), "HmacSHA1"));
        byte[] signature = mac.doFinal(str.getBytes(charset));
        String base64 = Base64.getEncoder().encodeToString(signature);

        System.out.println("签名结果："+base64);

        conn.setRequestProperty("_api_signature", base64);


        conn.setRequestProperty("Accept", "application/json;charset=utf-8");
        //conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        OutputStream os = conn.getOutputStream();
        String body = bodyBuf.substring(1);
        os.write(body.getBytes(charset));

        InputStream is = conn.getInputStream();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int n = -1;
        while((n= is.read(bytes))>-1){
            bos.write(bytes,0,n);
        }

        String result = new String(bos.toByteArray(),charset);

        System.out.println(result);
    }

    public static String httpRequest(String requestUrl,String requestMethod,String outputStr){
        StringBuffer buffer=null;
        try{
            URL url=new URL(requestUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod(requestMethod);
            conn.connect();
            //往服务器端写内容 也就是发起http请求需要带的参数
            if(null!=outputStr){
                OutputStream os=conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }

            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
