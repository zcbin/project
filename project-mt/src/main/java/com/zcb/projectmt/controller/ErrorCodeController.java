package com.zcb.projectmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.domain.ErrorCode;
import com.zcb.projectmt.service.IErrorCodeService;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
     * @param approval 审核状态
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/list")

    public JSONObject list(String errorCode, String approval,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit) {
        List<ErrorCode> errorCodeList = errorCodeService.listErrorCode(errorCode, approval, null, page, limit);
        return ResponseUtil.ok(errorCodeList);
    }

    @GetMapping(value = "/listByUser")
    public JSONObject listByUser(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit) {
        List<ErrorCode> errorCodeList = errorCodeService.listErrorCode(null, null, 1, page, limit);
        return ResponseUtil.ok(errorCodeList);
    }

    @PostMapping(value = "/insert")
    public JSONObject insert(@RequestBody ErrorCode errorCode) {
        String errorCodeString = errorCode.getErrorCode();
        if (errorCodeService.getErrorCode(errorCodeString) != null) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.PARAMETER_IS_REPEAT, "错误码已存在");
        }
        errorCode.setApplicantId(1); //申请人id
        errorCodeService.insertErrorCode(errorCode);
        return ResponseUtil.ok(errorCode);
    }

    @PostMapping(value = "/update")
    public JSONObject update(@RequestBody ErrorCode errorCode) {
        if (errorCode.getId() == null) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
        }
        if (errorCodeService.updateErrorCode(errorCode) == 0) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.UPDATE_FAILED, "更新失败");
        }
        return ResponseUtil.ok();
    }
    @PostMapping(value = "/delete")
    public JSONObject delete(@RequestBody ErrorCode errorCode) {
        Integer id = errorCode.getId();
        if (id == null) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
        }
        if (errorCodeService.deleteErrorCode(id) == 0) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.DELETE_FAILED, "删除失败");
        }
        return ResponseUtil.ok();
    }

    /**
     * 审核
     * @param errorCode
     * @return
     */
    @PostMapping(value = "/approval")
    public JSONObject approval(@RequestBody ErrorCode errorCode) {
        if (errorCode.getId() == null) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
        }
        errorCode.setApproverId(0001);
        errorCode.setApprovalTime(LocalDateTime.now());

        if (errorCodeService.updateErrorCode(errorCode) == 0) {
            return ResponseUtil.fail(com.zcb.projectmt.common.ErrorCode.UPDATE_FAILED, "更新失败");
        }
        return ResponseUtil.ok();
    }

}
