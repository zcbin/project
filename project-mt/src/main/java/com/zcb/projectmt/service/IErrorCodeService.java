package com.zcb.projectmt.service;

import com.zcb.projectmt.domain.ErrorCode;

import java.util.List;

/**
 * @author: zcbin
 * @title: IErrorCodeService
 * @packageName: com.zcb.projectmt.service
 * @projectName: project
 * @description: 错误码服务接口
 * @date: 2020/6/5 15:41
 */
public interface IErrorCodeService {
    /**
     * 新增
     * @param errorCode
     * @return
     */
    int insertErrorCode(ErrorCode errorCode);

    /**
     * 修改
     * @param errorCode
     * @return
     */
    int updateErrorCode(ErrorCode errorCode);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteErrorCode(Integer id);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    ErrorCode getErrorCode(Integer id);

    /**
     * 根据errorCode查找
     * @param errorCode
     * @return
     */
    ErrorCode getErrorCode(String errorCode);

    /**
     * 列表
     * @param desc
     * @param status
     * @param approval
     * @param page
     * @param limit
     * @return
     */
    List<ErrorCode> listErrorCode(String errorCode, String desc, String status, String approval, Integer page, Integer limit);
}
