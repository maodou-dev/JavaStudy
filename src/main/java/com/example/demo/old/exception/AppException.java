package com.example.demo.old.exception;

import org.springframework.stereotype.Component;

/**
 * @author zhengyd
 * @mail zhengyd@yinhai.com
 * @date 2020-09-17
 * @time 17:24
 */
@Component
public class AppException extends Exception {
    public AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}
