package com.zcb.projectmt.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: zcbin
 * @title: s
 * @packageName: com.zcb.projectmt.util
 * @projectName: project
 * @description:
 * @date: 2020/6/9 20:01
 */
public class ParseJsonUtil {
    public static Integer parseInteger(String body, String field) {
        JSONObject json = JSONObject.parseObject(body);
        return json.getInteger(field);
    }

    public static BigDecimal parseBigDecimal(String body, String field) {
        JSONObject json = JSONObject.parseObject(body);
        return json.getBigDecimal(field);
    }

    public static String parseString(String body, String field) {
        JSONObject json = JSONObject.parseObject(body);
        return json.getString(field);
    }

    public static List<Integer> parseListInteger(String body, String field) {
        JSONObject json = JSONObject.parseObject(body);
        return json.getObject(field, new TypeReference<List<Integer>>() {
        });
    }

    public static List<String> parseStringList(String body, String field) {
        JSONObject json = JSONObject.parseObject(body);
        return json.getObject(field, new TypeReference<List<String>>() {
        });
    }
}
