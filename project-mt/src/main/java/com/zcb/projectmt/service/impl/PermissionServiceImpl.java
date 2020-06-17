package com.zcb.projectmt.service.impl;

import com.zcb.projectmt.dao.PermissionMapper;
import com.zcb.projectmt.dao.RolePermissionMapper;
import com.zcb.projectmt.domain.Permission;
import com.zcb.projectmt.domain.PermissionExample;
import com.zcb.projectmt.domain.RolePermission;
import com.zcb.projectmt.domain.RolePermissionExample;
import com.zcb.projectmt.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zcbin
 * @title: PermissionServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description:
 * @date: 2020/6/16 20:21
 */
@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public List<Permission> listPermission(List<Integer> roleId) {
        if (roleId == null) {
            return null;
        }
        RolePermissionExample rolePermissionExample = new RolePermissionExample();
        RolePermissionExample.Criteria rolePermissionCriteria = rolePermissionExample.createCriteria();
        rolePermissionCriteria.andRoleIdIn(roleId);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectByExample(rolePermissionExample);
        List<Integer> permissionIds = rolePermissionList.stream()
                .map(RolePermission::getPermissionId).collect(Collectors.toList());
        if (permissionIds == null) {
            return null;
        }
        PermissionExample permissionExample = new PermissionExample();
        PermissionExample.Criteria permissionCriteria = permissionExample.createCriteria();
        permissionCriteria.andIdIn(permissionIds);
        return permissionMapper.selectByExample(permissionExample);
    }
}
