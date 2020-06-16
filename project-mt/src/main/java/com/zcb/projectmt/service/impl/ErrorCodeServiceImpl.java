package com.zcb.projectmt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zcb.projectmt.dao.ErrorCodeMapper;
import com.zcb.projectmt.domain.ErrorCode;
import com.zcb.projectmt.domain.ErrorCodeExample;
import com.zcb.projectmt.service.IErrorCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: zcbin
 * @title: ErrorCodeServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description:
 * @date: 2020/6/5 15:53
 */
@Service
public class ErrorCodeServiceImpl implements IErrorCodeService {
    @Autowired
    private ErrorCodeMapper errorCodeMapper;
    @Override
    public int insertErrorCode(ErrorCode errorCode) {
        errorCode.setAddTime(LocalDateTime.now());
        return errorCodeMapper.insertSelective(errorCode);
    }

    @Override
    public int updateErrorCode(ErrorCode errorCode) {
        errorCode.setUpdateTime(LocalDateTime.now());
        return errorCodeMapper.updateByPrimaryKeySelective(errorCode);
    }

    @Override
    public int deleteErrorCode(Integer id) {
        return errorCodeMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public ErrorCode getErrorCode(Integer id) {
        return errorCodeMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }

    @Override
    public ErrorCode getErrorCode(String errorCode) {
        ErrorCodeExample example = new ErrorCodeExample();
        ErrorCodeExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(errorCode)) {
            return null;
        }
        criteria.andErrorCodeEqualTo(errorCode);
        criteria.andDeletedEqualTo(false);
        return errorCodeMapper.selectOneByExample(example);
    }

    @Override
    public List<ErrorCode> listErrorCode(String errorCode, String approval, Integer applicantId, Integer page, Integer limit) {
        ErrorCodeExample example = new ErrorCodeExample();
        ErrorCodeExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(errorCode)) {
            criteria.andErrorCodeEqualTo(errorCode);
        }
        if (!StringUtils.isEmpty(approval)) {
            criteria.andApprovalEqualTo(approval);
        }
        if (!StringUtils.isEmpty(applicantId)) {
            criteria.andApplicantIdEqualTo(applicantId);
        }
        criteria.andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return errorCodeMapper.selectByExample(example);
    }
}
