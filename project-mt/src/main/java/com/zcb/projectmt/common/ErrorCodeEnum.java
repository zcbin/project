package com.zcb.projectmt.common;

/**
 * @author: zcbin
 * @title: ErrorCodeEnum
 * @packageName: com.zcb.projectmt.util
 * @projectName: project
 * @description: 通用错误码
 * @date: 2020/6/17 14:39
 */
public enum ErrorCodeEnum {
    SUCCESSFUL_OPERATION("00000", "成功"),

    CLIENT_ERROR("A0001", "用户端错误"),

    REGISTRATION_ERROR("A0100", "用户注册错误"),

    USERNAME_VERIFICATION_FAILED("A0110", "用户名校验失败"),
    USERNAME_ALREADY_EXISTS("A0111", "用户名已存在"),
    USERNAME_NO_EXISTS("A0112", "用户不存在"),

    PASSWORD_VERIFICATION_FAILED("A0120", "密码校验失败"),
    PASSWORD_LENGTH_NOT_ENOUGH("A0121", "密码长度不够"),
    PASSWORD_STRENGTH_NOT_ENOUGH("A0122", "密码强度不够"),

    LOGIN_EXPIRED("A0230", "用户登录过期"),
    NOT_LOGGED_IN("A0231", "用户未登录"),
    USERNAME_PASSWORD_FAILED("A0232", "用户名或密码错误"),

    ABNORMAL_PERMISSIONS("A0300", "访问权限异常"),
    UNAUTHORIZED("A0301", "无权限"),

    PARAMETER_ERROR("A0400", "用户请求参数错误"),
    PARAMETER_IS_REPEAT("A0401", "用户请求参数重复"),
    PARAMETER_IS_EMPTY("A0402", "用户请求参数必填为空"),

    REQUEST_NOT_FOUND("A0410", "用户请求URL不存在"), //404
    REQUEST_METHOD_NOT_SUPPORT("A0411", "不支持的请求方法"),

    SYSTEM_ERROR("B0001", "出错了"), //系统执行出错
    OPERATION_FAILED("B0100", "操作失败"),

    UPDATE_FAILED("B0101", "更新失败"),
    INSERT_FAILED("B0102", "新增失败"),

    DELETE_FAILED("B0110", "删除失败");


    private String errorCode;
    private String errorMessage;
    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public String getErrorCodeCode() {
        return this.errorCode;
    }
    public String getErrorMessageMessage() {
        return this.errorMessage;
    }
}
