package com.zcb.projectmt.exception;

import com.zcb.projectmt.common.BaseException;
import com.zcb.projectmt.common.ErrorCodeEnum;

/**
 * @author: zcbin
 * @title: NotFoundException
 * @packageName: com.zcb.projectmt.exception
 * @projectName: project
 * @description:
 * @date: 2020/6/17 16:40
 */
public class NotFoundException extends BaseException {
    public NotFoundException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }
}
