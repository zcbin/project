package com.zcb.projectmt.common;

/**
 * @author: zcbin
 * @title: BaseException
 * @packageName: com.zcb.projectmt.common
 * @projectName: project
 * @description: 异常基类
 * @date: 2020/6/17 15:37
 */
public class BaseException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    private Object data;
    public BaseException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getErrorMessageMessage());
        this.errorCode = errorCodeEnum.getErrorCodeCode();
        this.errorMessage = errorCodeEnum.getErrorMessageMessage();
    }
    public BaseException(ErrorCodeEnum errorCodeEnum, Object data) {
        this(errorCodeEnum);
        this.data = data;
    }
    public BaseException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public BaseException(String errorCode, String errorMessage, Object data) {
        this(errorCode, errorMessage);
        this.data = data;
    }
}
