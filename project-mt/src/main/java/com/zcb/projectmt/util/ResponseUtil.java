package com.zcb.projectmt.util;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.common.ErrorCode;
import com.zcb.projectmt.common.ErrorCodeEnum;

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
        obj.put("errorCode", ErrorCodeEnum.SUCCESSFUL_OPERATION.getErrorCodeCode());
        obj.put("errorMessage", ErrorCodeEnum.SUCCESSFUL_OPERATION.getErrorMessageMessage());
        return obj;
    }

    public static JSONObject ok(Object data) {
        JSONObject obj = new JSONObject();
        obj.put("errorCode", ErrorCodeEnum.SUCCESSFUL_OPERATION.getErrorCodeCode());
        obj.put("errorMessage", ErrorCodeEnum.SUCCESSFUL_OPERATION.getErrorMessageMessage());
        obj.put("data", data);
        return obj;
    }

    public static JSONObject ok(String errorMessage, Object data) {
        JSONObject obj = new JSONObject();
        obj.put("errorCode", ErrorCodeEnum.SUCCESSFUL_OPERATION.getErrorCodeCode());
        obj.put("errorMessage", errorMessage);
        obj.put("data", data);
        return obj;
    }

    public static JSONObject fail() {
        JSONObject obj = new JSONObject();
        obj.put("errorCode", ErrorCode.SYSTEM_ERROR);
        obj.put("errorMessage", "出错了");
        return obj;
    }

    /**
     * 系统定义错误码及消息
     * @param errorCodeEnum
     * @return
     */
    public static JSONObject fail(ErrorCodeEnum errorCodeEnum) {
        JSONObject obj = new JSONObject();
        obj.put("errorCode", errorCodeEnum.getErrorCodeCode());
        obj.put("errorMessage", errorCodeEnum.getErrorMessageMessage());
        return obj;
    }
    public static JSONObject fail(ErrorCodeEnum errorCodeEnum, String errorMessage) {
        JSONObject obj = new JSONObject();
        obj.put("errorCode", errorCodeEnum.getErrorCodeCode());
        obj.put("errorMessage", errorMessage);
        return obj;
    }
    /**
     * 自定义错误码及消息
     * @param error_code
     * @param error_message
     * @return
     */
    public static JSONObject fail(String error_code, String error_message) {
        JSONObject obj = new JSONObject();
        obj.put("errorCode", error_code);
        obj.put("errorMessage", error_message);
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


