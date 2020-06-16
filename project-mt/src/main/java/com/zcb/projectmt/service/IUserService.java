package com.zcb.projectmt.service;

import com.zcb.projectmt.domain.User;

import java.util.List;

/**
 * @author: zcbin
 * @title: IUserService
 * @packageName: com.zcb.projectmt.service
 * @projectName: project
 * @description: 用户接口
 * @date: 2020/6/11 20:14
 */
public interface IUserService {
    List<User> listUser(String username);
    User getUser(String username);
    int insertUser(User user);
    int updateUser(User user);
    int deleteUser(Integer id);
}
