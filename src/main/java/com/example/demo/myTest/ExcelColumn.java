package com.example.demo.myTest;

import java.lang.annotation.*;

/**
 * 自定义实体类所需要的bean(Excel属性标题、位置等)
 *
 * @author yangjie
 * @mail yangjie@yinhai.com
 * @date 2021/3/1
 * @time 19:01
 * @since 1.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     *
     * @return
     * @author yangjie
     */
    String value() default "";

    /**
     * Excel从左往右排列位置
     *
     * @return
     * @author yangjie
     */
    int col() default 0;
}
