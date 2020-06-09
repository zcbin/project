package com.zcb.projectmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.domain.ErrorCode;
import com.zcb.projectmt.service.IErrorCodeService;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zcbin
 * @title: ErrorCodeController
 * @packageName: com.zcb.projectmt.controller
 * @projectName: project
 * @description: 错误码
 * @date: 2020/6/8 10:40
 */
@RestController
@RequestMapping(value = "/mt/errorcode")
public class ErrorCodeController {
    @Autowired
    private IErrorCodeService errorCodeService;

    /**
     * 列表
     * @param errorCode 错误码
     * @param desc 描述
     * @param status 状态
     * @param approval 审核状态
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/list")
    public JSONObject list(String errorCode, String desc,
                           String status, String approval,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit) {
        List<ErrorCode> errorCodeList = errorCodeService.listErrorCode(errorCode, desc, status, approval, page, limit);
        return ResponseUtil.ok(errorCodeList);
    }

    @PostMapping(value = "/insert")
    public JSONObject insert(@RequestBody ErrorCode errorCode) {
        String errorCodeString = errorCode.getErrorCode();
        if (errorCodeService.getErrorCode(errorCodeString) != null) {
            return ResponseUtil.fail(com.zcb.projectmt.util.ErrorCode.PARAMETER_IS_REPEAT, "错误码已存在");
        }
        errorCodeService.insertErrorCode(errorCode);
        return ResponseUtil.ok();
    }

    @PostMapping(value = "/update")
    public JSONObject update(@RequestBody ErrorCode errorCode) {
        if (errorCode.getId() == null) {
            return ResponseUtil.fail(com.zcb.projectmt.util.ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
        }
        String errorCodeString = errorCode.getErrorCode();
        if (errorCodeString != null && errorCodeService.getErrorCode(errorCodeString) != null) {
            return ResponseUtil.fail(com.zcb.projectmt.util.ErrorCode.PARAMETER_IS_REPEAT, "错误码已存在");
        }
        if (errorCodeService.updateErrorCode(errorCode) == 0) {
            return ResponseUtil.fail(com.zcb.projectmt.util.ErrorCode.UPDATE_FAILED, "更新失败");
        }
        return ResponseUtil.ok();
    }
    @PostMapping(value = "/delete")
    public JSONObject delete(@RequestBody ErrorCode errorCode) {
        Integer id = errorCode.getId();
        if (id == null) {
            return ResponseUtil.fail(com.zcb.projectmt.util.ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
        }
        if (errorCodeService.deleteErrorCode(id) == 0) {
            return ResponseUtil.fail(com.zcb.projectmt.util.ErrorCode.DELETE_FAILED, "删除失败");
        }
        return ResponseUtil.ok();
    }

}