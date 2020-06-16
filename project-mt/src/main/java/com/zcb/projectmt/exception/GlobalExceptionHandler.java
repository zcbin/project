package com.zcb.projectmt.exception;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.util.ErrorCode;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: zcbin
 * @title: GlobalExceptionHa
 * @packageName: com.zcb.projectmt.exception
 * @projectName: project
 * @description:
 * @date: 2020/6/16 15:41
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 用户名或密码校验失败
     * @param e
     * @return
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public JSONObject badCredentialsException(BadCredentialsException e) {
        System.out.println("用户名/密码错误");
        return ResponseUtil.fail(ErrorCode.USERNAME_PASSWORD_FAILED, "用户名或密码错误");
    }
    @ExceptionHandler(value = Exception.class)
    public JSONObject exception(Exception e) {
        System.out.println("e=" + e);
        return ResponseUtil.fail(ErrorCode.SYSTEM_ERROR, "出错了");
    }
}
