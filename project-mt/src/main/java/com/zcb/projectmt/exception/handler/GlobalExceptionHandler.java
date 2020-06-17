package com.zcb.projectmt.exception.handler;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.common.ErrorCodeEnum;
import com.zcb.projectmt.exception.NotFoundException;
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
        return ResponseUtil.fail(ErrorCodeEnum.USERNAME_PASSWORD_FAILED);
    }

    /**
     * 404
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotFoundException.class)
    public JSONObject notFoundException(BadCredentialsException e) {
        return ResponseUtil.fail(ErrorCodeEnum.REQUEST_NOT_FOUND);
    }
    @ExceptionHandler(value = Exception.class)
    public JSONObject exception(Exception e) {
        System.out.println("e=" + e);
        return ResponseUtil.fail(ErrorCodeEnum.SYSTEM_ERROR);
    }
}
