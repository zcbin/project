package com.zcb.projectmt.exception;

import com.zcb.projectmt.common.BaseException;
import com.zcb.projectmt.common.ErrorCodeEnum;

/**
 * @author: zcbin
 * @title: SecurityException
 * @packageName: com.zcb.projectmt.exception.handler
 * @projectName: project
 * @description: spring security异常
 * @date: 2020/6/17 15:47
 */
public class SecurityException extends BaseException {
    public SecurityException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public SecurityException(ErrorCodeEnum errorCodeEnum, Object data) {
        super(errorCodeEnum, data);
    }

    public SecurityException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public SecurityException(String errorCode, String errorMessage, Object data) {
        super(errorCode, errorMessage, data);
    }
}
