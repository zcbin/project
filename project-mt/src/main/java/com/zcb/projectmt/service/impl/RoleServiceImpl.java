package com.zcb.projectmt.service.impl;

import com.zcb.projectmt.dao.RoleMapper;
import com.zcb.projectmt.dao.UserRoleMapper;
import com.zcb.projectmt.domain.Role;
import com.zcb.projectmt.domain.RoleExample;
import com.zcb.projectmt.domain.UserRole;
import com.zcb.projectmt.domain.UserRoleExample;
import com.zcb.projectmt.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zcbin
 * @title: RoleServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description:
 * @date: 2020/6/16 19:51
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Override
    public List<Role> listRole(Integer userId) {
        if (userId == null) {
            return null;
        }
        UserRoleExample userRoleExample = new UserRoleExample();
        UserRoleExample.Criteria userRoleCriteria = userRoleExample.createCriteria();
        userRoleCriteria.andUserIdEqualTo(userId);
        List<UserRole> userRoleList = userRoleMapper.selectByExample(userRoleExample);

        List<Integer> roleIds = userRoleList.stream()
                .map(UserRole::getRoleId).collect(Collectors.toList());
        RoleExample example = new RoleExample();
        RoleExample.Criteria criteria = example.createCriteria();
        if (roleIds == null || roleIds.size() == 0) {
            return null;
        }
        criteria.andIdIn(roleIds);
        return roleMapper.selectByExample(example);
    }
}
