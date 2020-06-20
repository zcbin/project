package com.zcb.projectmt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: zcbin
 * @title: ApiDesc
 * @packageName: com.zcb.projectmt.annotation
 * @projectName: project
 * @description:
 * @date: 2020/6/18 10:18
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiDesc {
    String[] menu();
    String button();
}

