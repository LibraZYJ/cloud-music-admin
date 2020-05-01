package com.soft1851.music.admin.annotation;

import java.lang.annotation.*;

/**
 * @author Yujie_Zhao
 * @Date: 2020/4/30 20:34
 * @Description:
        */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogDirection {
    /**
     * 接口信息
     * @return
     */
    String direction() default "";
}
