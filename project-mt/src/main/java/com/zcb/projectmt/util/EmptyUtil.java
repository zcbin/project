package com.zcb.projectmt.util;

import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * @author: zcbin
 * @title: EmptyUtil
 * @packageName: com.zcb.projectmt.util
 * @projectName: project
 * @description:
 * @date: 2020/6/19 14:45
 */
public class EmptyUtil {
    public static boolean isEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }
    public static boolean isEmpty(Object obj) {
        return StringUtils.isEmpty(obj) ? true : false;
    }
}
