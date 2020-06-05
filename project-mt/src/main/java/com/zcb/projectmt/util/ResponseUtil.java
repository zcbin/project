package com.zcb.projectmt.util;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应操作结果
 * <pre>
 *  {
 *      error_code: 错误码，
 *      error_message: 错误信息，
 *      data: 响应数据
 *  }
 * </pre>
 *
 */
public class ResponseUtil {
    public static JSONObject ok() {
        JSONObject obj = new JSONObject();
        obj.put("error_code", ErrorCode.SUCCESSFUL_OPERATION);
        obj.put("error_message", "成功");
        return obj;
    }

    public static JSONObject ok(Object data) {
        JSONObject obj = new JSONObject();
        obj.put("error_code", ErrorCode.SUCCESSFUL_OPERATION);
        obj.put("error_message", "成功");
        obj.put("data", data);
        return obj;
    }

    public static JSONObject ok(String error_message, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("error_code", ErrorCode.SUCCESSFUL_OPERATION);
        obj.put("error_message", error_message);
        obj.put("data", data);
        return obj;
    }

    public static JSONObject fail() {
        JSONObject obj = new JSONObject();
        obj.put("error_code", ErrorCode.SYSTEM_ERROR);
        obj.put("error_message", "出错了");
        return obj;
    }

    public static JSONObject fail(String error_code, String error_message) {
        JSONObject obj = new JSONObject();
        obj.put("error_code", error_code);
        obj.put("error_message", error_message);
        return obj;
    }

    public static JSONObject parameterError() {
        return fail(ErrorCode.PARAMETER_ERROR, "参数不对");
    }

    public static JSONObject parameterIsEmpty() {
        return fail(ErrorCode.PARAMETER_IS_EMPTY, "参数不能为空");
    }

    public static JSONObject unlogin() {
        return fail(ErrorCode.NOT_LOGGED_IN, "请登录");
    }

    public static JSONObject serious() {
        return fail(ErrorCode.SYSTEM_ERROR, "系统内部错误");
    }

    public static JSONObject unauthz() {
        return fail(ErrorCode.UNAUTHORIZED, "无操作权限");
    }
    public static JSONObject unloginTimeOut() {
        return fail(ErrorCode.LOGIN_EXPIRED, "会话超时，请重新登录");
    }
}


