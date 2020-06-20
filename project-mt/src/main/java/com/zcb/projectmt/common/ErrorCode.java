package com.zcb.projectmt.common;

/**
 * @author: zcbin
 * @title: ErrorCode
 * @packageName: com.zcb.projectmt.util
 * @projectName: project
 * @description: 通用错误码
 * @date: 2020/6/4 10:54
 */
@Deprecated
public interface ErrorCode {
    String SUCCESSFUL_OPERATION = "00000"; // 操作成功

    /*A 表示错误来源于用户*/
    String CLIENT_ERROR = "A0001"; // 用户端错误

    String REGISTRATION_ERROR = "A0100"; //用户注册错误

    String USERNAME_VERIFICATION_FAILED = "A0110"; //用户名校验失败
    String USERNAME_ALREADY_EXISTS = "A0111"; //用户名已存在
    String USERNAME_NO_EXISTS = "A0112"; //用户不存在

    String PASSWORD_VERIFICATION_FAILED = "A0120"; //密码校验失败
    String PASSWORD_LENGTH_NOT_ENOUGH = "A0121"; //密码长度不够
    String PASSWORD_STRENGTH_NOT_ENOUGH = "A0122"; //密码强度不够

    String LOGIN_EXPIRED = "A0230"; //用户登录过期
    String NOT_LOGGED_IN = "A0231"; //用户未登录
    String USERNAME_PASSWORD_FAILED = "A0232"; //用户名或密码错误

    String ABNORMAL_PERMISSIONS = "A0300"; //访问权限异常
    String UNAUTHORIZED = "A0301"; // 无权限

    String PARAMETER_ERROR = "A0400"; // 用户请求参数错误
    String PARAMETER_IS_REPEAT = "A0401"; //用户请求参数重复

    String PARAMETER_IS_EMPTY = "A0410"; //用户请求参数必填为空


    /*B 表示错误来源于当前系统*/
    String SYSTEM_ERROR = "B0001"; // 系统执行出错

    String OPERATION_FAILED = "B0100"; //操作失败
    String UPDATE_FAILED = "B0101"; //更新失败

    String DELETE_FAILED = "B0110"; //删除失败




}
