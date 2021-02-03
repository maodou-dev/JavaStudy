package com.example.demo.old.asp;//package com.zyd.demo.asp;
//
//import com.zyd.demo.exception.AppException;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * @author zhengyd
// * @mail zhengyd@yinhai.com
// * @date 2020-09-17
// * @time 14:37
// */
//@Aspect
//@Component
//public class MyAsp {
//
//    @Pointcut("execution(* com.zyd.demo.test.TestController.*(..))")
//    public void doAround(){
//        System.out.println("321");
//    }
//
//    @Around("doAround()") //around 与 下面参数名around对应
//    public Object processAuthority(ProceedingJoinPoint point) {
//        String s = "";
//        try {
//            s = point.proceed().toString(); //调用目标方法
//            System.out.println(s);
//            s = "after";
//        } catch (Throwable throwable) {
//            if (throwable instanceof AppException) {
//                if (throwable == null) {
//                    System.out.println(throwable.getMessage());
//                    return throwable.getMessage();
//                } else {
//                    System.out.println(throwable.getMessage());
//                    return throwable.getMessage();
//                }
//            }
//        }
//        System.out.println("success");
//        return "3";
//    }
//}
