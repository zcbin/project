package com.zcb.projectmt.service;

import com.zcb.projectmt.domain.Role;

import java.util.List;

/**
 * @author: zcbin
 * @title: IRoleService
 * @packageName: com.zcb.projectmt.service
 * @projectName: project
 * @description:
 * @date: 2020/6/16 19:48
 */
public interface IRoleService {
    List<Role> listRole(Integer userId);
}
