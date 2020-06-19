package cn.jmu.marxism.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 主要需要教师权限的方法
 * @author PlumK
 * @version 1.0
 * @date 2020/6/19 12:20
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TeacherOnly {
    boolean required() default true;
}
