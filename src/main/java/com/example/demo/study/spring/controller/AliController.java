package com.example.demo.study.spring.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.demo.study.spring.util.QRCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.alipay.api.AlipayConstants.APP_ID;
//import org.apache.commons.lang3.StringUtils;

/**
 * TODO
 *
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2021/5/14
 * @time 22:24
 */
@RestController
@RequestMapping("/ali")
public class AliController {

    public static String APPID = "2021002144670371";
//    public static String APPID = "2021000117659174";
    // 私钥 pkcs8格式的 因为公钥我用的是自己申请的那个(我的第一个支付中心)里面的那个公钥，所以私钥填的也是我的第一个中心里面对应公钥生成的私钥
    //公钥类似于一把锁，私钥类似于钥匙，一个公钥对应一个私钥，1对1关系
    public static String RSA_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCThi7LME4g0crI5MQnSLwHJdNhD100wEfFRvtkksznmyI08vXv1wok0j54n+YbvuOuEa3jczA0QemW9vtaug82gsvGpNs6CH9OuslFXPuMkuk48T7W4saH23O7d95PORxse5hWCLNpHLxB/5THfHqeIUdfIp+ZJJFAaOCZs7QVYdnw03p1Vm8BnKVWR/Bfp7/67kGjN3atS95kQs7ytUC1GwZb0x4dmeLwISjenDD9xUvBNhBeq/76GgfQEAGY73j+bbf6ahcl4J0QuyxtIptr+l+kndBgrw7WGwPcNGvMGopU8mepEbHwhxT5vgkM0GiF+SCP+2Rf/Mt7LG9l41EnAgMBAAECggEAJfqdgWHmOzzHYADFHvfysERkrtAtxq7Zy0gC4FmKtvWRUOBbwGs0CVa3DobbzrjOFLooci3tcwalBGCBRVIxKG9z1qgKaF5XqAUeW+UcPYx0zlwNOXUYd1Ff8hth+DNTxfnrtfp3ddAYB0WtH85EIwGeRje7rsljO5nEeM1yxy4NxPk8BulXRTJroX9KPBUx+0ItYhOElmbkKb4Y2CySRIsBL355l8d98VlRmgkLta0ZY1ZUSS7kjjtK5Am+v5tvUciTTzgkpfY3tbjYLQ57w2jtLP7yU/Ul4313lAV/Gz258jq//cUMotYFI722QelDrN8CD7pLlPexX8ps+1RRgQKBgQDD5HMfMkGBNAuumVp45SYRpbwNeQ1lE3oaWNsaQeSfzXrXsqKwWby3aJToT+8wQeVxlNRDMG1gT39tO1tu7vqCCj7uo1VnKsLZ7XK+OJImSvd0rKn5pO1KGt6S//XFpGvTEisTy/GmfacF8mipDwbuspk9O2sKfBSHT9IVZ7IabwKBgQDAylu6i/PRiuaHATkIXEpb5+MNIr8HIfXHMODuAqrXRKXM6I6bT4+jzw33rPtUgjRZntoZauN+iwvWrSeaaXf0cmydhgEV+QJdW5jRpq+kE3dwKK/AmO1wXAUK/wFNaWZKvmXxiM7NTTSqAo5lN3OxkclEF841ACPkKXl5lkRwyQKBgQCOBWCZa3wmSi92BGSdZbT18mymb+8QKtDJrUYcgU1ZtkWrgC2HIWg6qK3cx6coG35XnIlPtRp3Suudq/0cLlKcWQVLSkuuokfma8pWsvjynkNN6ajJ/5KxuU1I0h3DjvNFcdSdY6ztAghHvXm3p9dk00NaK2vtjAvuFia/shBkLQKBgD+8JmsbjXV+uAt1ygcLi8GqfZOAIysWfQxnClVlKFmWZkHBuDN8L/51myaAV25uxSPDslKo1aCmEznbcLZJm9CCa4oAHSfIdJGskG7u7i4OCXRY3xGK/SQiJ8Ck4SdYaurW2xjoo4WXuoth4PVxSZwwmBscy50V+MebNn+Nv/6RAoGAIEJasLzwtCe5HCKjQJc4kjI9K0mN47+AV0ULU+ZMhIuX41QbPvZ1JuhUYqXfWHV1AgRQcYRqj0/MPtXHQdP24pQF8Hs4qfDXLT4exG6F0IzJ/YP8SHMJAeky72Rq/9pIoK6ob6XMt43NGerQNy2KAski6DuSsTDSYbDa8x/8sKo=";
//    public static String RSA_PRIVATE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhb1g5XxdtP3i9NKHG93jpgy0zEj+80/2QgUNZ/Pid36viBu70Ko5YlYIHjhHTiUFTOZRBZXPlmHYdN9ASS45Z82WHQHyO4vPsIlywESF1RCZViuLKFyQalw8uvxE+mpXtKqKlzI1DdCq6j8ZG7ue6vcqyRzrRgzqFzvoOeyGeC8iz9WCQnZDiOpJ7q//x0jaGoZjuJoJXSN2Nc1gskLa3zFHbDNwJbmhTHEtFJUg7TUWhZ4efCjsxMF0U5A9Y+1i8pX7zAm29Pa+gLRXeYjV6vXP5lLSSJ2lPBcsoiCcZTyo5ukXv2PdXf6icgfMewGMDao1u+GL/TkaLDpmHcEUgwIDAQAB";
    public static String URL = "https://openapi.alipay.com/gateway.do";
//    public static String URL = "https://openapi.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥 这里我填的是沙箱应用里面RSA2(SHA256)密钥(推荐)这个生成的支付宝的公钥,正式环境是接口加签方式里面的支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArRF/GUyI3V7zlzLu8wKCESNvVBwTgSlpJevddy+nHCm86e5W4pwEyiuxkE1GasfJ9X7D+Nho81RUw2iAl/3gRU87WadzIo1RH04OE/Jlmy72xl/TkBBwJmpCslP7b+oTTKw+AREkPyRHwx4wHspD4XmNSwn4XEk85pHlTe6M+HDD1Wa+xtBmyKXalowLHEVjdVk5tM9Qux+s8Jw/EPPjZ30OXlSgUf3afRBYiDObyXTiPaRxBTY65g7cbWx3OJ4Rwy/ZvWprS+mOYIH9lMOp1H67flNGT668FBfxXoUAC8Egp09nzRP+OdK/Pxi0sX6qh4DJIg5tsbgCBK89i3UnewIDAQAB ";
//    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxFfPXSUSzMPqhodo5u59iGoqyPlQuEkZxCtywq49O+RhDhUiFXX5hhJOFpNrLvyzlKGZM50PGfh18vwUz77sndq/Vuwq84urfXk1NZbBymZYiNjkmiS14/RY0kjp0HjBQL2kPJ0JippkJ5k0k0KArre8Nn8JLDtIy2PQoF+EKC41wKsEEfcUPg885zGPo7KcO4J5Sj4wIeyPSMFxpXjM3/K8SjGoDEnvbDVswaQ6qDF4l3NqhGHFXZI1wePb9uSo/W4bknS6PD7Hp/GPsGXIkOvu5r6IEMQBR5D4EmR6Xt10LYNsEvnzuXormpkdDsf7HepF5DgN9PlictoZo3pqFQIDAQAB";    public static String log_path = "E:/logs/";
    // RSA2
    public static String SIGNTYPE = "RSA2";

    AlipayClient alipayClient = new DefaultAlipayClient(URL, APPID, RSA_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
//    AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2021000117659174","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhb1g5XxdtP3i9NKHG93jpgy0zEj+80/2QgUNZ/Pid36viBu70Ko5YlYIHjhHTiUFTOZRBZXPlmHYdN9ASS45Z82WHQHyO4vPsIlywESF1RCZViuLKFyQalw8uvxE+mpXtKqKlzI1DdCq6j8ZG7ue6vcqyRzrRgzqFzvoOeyGeC8iz9WCQnZDiOpJ7q//x0jaGoZjuJoJXSN2Nc1gskLa3zFHbDNwJbmhTHEtFJUg7TUWhZ4efCjsxMF0U5A9Y+1i8pX7zAm29Pa+gLRXeYjV6vXP5lLSSJ2lPBcsoiCcZTyo5ukXv2PdXf6icgfMewGMDao1u+GL/TkaLDpmHcEUgwIDAQAB","json","utf-8",
//        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxFfPXSUSzMPqhodo5u59iGoqyPlQuEkZxCtywq49O+RhDhUiFXX5hhJOFpNrLvyzlKGZM50PGfh18vwUz77sndq/Vuwq84urfXk1NZbBymZYiNjkmiS14/RY0kjp0HjBQL2kPJ0JippkJ5k0k0KArre8Nn8JLDtIy2PQoF+EKC41wKsEEfcUPg885zGPo7KcO4J5Sj4wIeyPSMFxpXjM3/K8SjGoDEnvbDVswaQ6qDF4l3NqhGHFXZI1wePb9uSo/W4bknS6PD7Hp/GPsGXIkOvu5r6IEMQBR5D4EmR6Xt10LYNsEvnzuXormpkdDsf7HepF5DgN9PlictoZo3pqFQIDAQAB","RSA2" );


    // 获取二维码
    @RequestMapping("/getQrCode")
    private Object getQrCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // todo 生成二维码，或者调用接口生成
        Map map = new HashMap();
        map.put("id", "1");
        map.put("name", "郑");
        String text = JSON.toJSONString(map);  //这里设置自定义网站url
        String destPath = "D:\\qrCode";
        // 生成图片并返回文件名
        String fileName = QRCodeUtils.encode(text, null, destPath, false);
        Map result = new HashMap();
        result.put("random", fileName);
        HttpSession session = request.getSession();
        session.setAttribute("file", result);
        return result;
//        System.out.println("getQrCode");
//        return null;
    }

    @GetMapping("/redirect")
    private void redirect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String header = request.getHeader("user-agent");
//        Enumeration<String> headerNames = request.getHeaderNames();
//        System.out.println(headerNames);
//        response.sendRedirect("https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021002144670371&scope=auth_user&redirect_uri=http%3A%2F%2F192.168.43.41%3A8888%2Ftest%2Fali%2FredirectMethod&state=init");
        response.sendRedirect("alipays://platformapi/startapp?appId=2021001123625885&page=pages%2Findex%2Findex%3FchInfo%3DXSyibaochaxunqianzhi%26returnUrl%3Dhttp%253A%252F%252F192.168.43.41%253A8888%252Ftest%252Fali%252FredirectMethod");
        // pages%2Findex%2Findex%3FchInfo%3DXSyibaochaxunqianzhi%26returnUrl%3Dhttp%253A%252F%252F192.168.43.41%253A8888%252Ftest%252Fali%252FredirectMethod
//        response.sendRedirect("https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm?app_id=2021000117659174&scope=auth_user&redirect_uri=http%3A%2F%2F192.168.43.41%3A8888%2Ftest%2Fali%2FredirectMethod&state=init");
    }

    @RequestMapping("/{fileName}")
    private Object valid(@PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // 获取文件
//        File file = new File("D:\\qrCode\\" + fileName);
//        HttpSession session = request.getSession();
//        Map fileInfo = (Map) session.getAttribute("file");
        // 调用支付宝接口，获取用户信息

//        if (fileInfo.get("random") != fileName) {
//
//        }
//        System.out.println("valid" + fileName);
        response.sendRedirect("https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=2021002144670371&scope=auth_user&redirect_uri=http%3A%2F%2F192.168.43.41%3A8888%2Ftest%2Fali%2FredirectMethod&state=init");
        Map result = new HashMap();
        result.put("message", "未扫码");
        result.put("status", "0");
        return result;
    }

    // 支付宝回调地址
    @GetMapping("/redirectMethod")
    private void redirectMethod(HttpServletRequest request1, HttpServletResponse response) {
        // 获取返回的authCode
        String authCode = request1.getParameter("auth_code");
        String appId = request1.getParameter("app_id");
        String source = request1.getParameter("source");
        String scope = request1.getParameter("scope");

        if (!"".equals(authCode)) {
            // 通过authCode换取 access_token 和 userId
//            AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", APPID, RSA_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
            AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
//            AlipayUserInfoShareRequest
            request.setCode(authCode);
            request.setGrantType("authorization_code");
            try {
                AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
                String accessToken = oauthTokenResponse.getAccessToken();
                System.out.println(JSONObject.toJSON(oauthTokenResponse));
                getUserInfo(accessToken);
            } catch (AlipayApiException e) {
                //处理异常
                e.printStackTrace();
            }

        }
    }

    private Object getUserInfo(String auth_token) {
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();
        try {
            AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(request, auth_token);
            System.out.println(userinfoShareResponse.getBody());
            return userinfoShareResponse.getBody();
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
        return null;
    }


}
