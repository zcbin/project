package com.zcb.projectmt.service;

import com.zcb.projectmt.domain.Permission;

import java.util.List;

/**
 * @author: zcbin
 * @title: IPermissionService
 * @packageName: com.zcb.projectmt.service
 * @projectName: project
 * @description:
 * @date: 2020/6/16 19:50
 */
public interface IPermissionService {
    List<Permission> listPermission(List<Integer> roleId);
}
