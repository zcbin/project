package com.zcb.projectmt.service.impl;

import com.zcb.projectmt.dao.UserMapper;
import com.zcb.projectmt.domain.User;
import com.zcb.projectmt.domain.UserExample;
import com.zcb.projectmt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: zcbin
 * @title: UserServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description: 用户服务
 * @date: 2020/6/11 20:17
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> listUser(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameEqualTo(username);
        }
        criteria.andDeletedEqualTo(false);
        return userMapper.selectByExample(example);
    }

    @Override
    public User getUser(String username) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(username)) {
            criteria.andUsernameEqualTo(username);
        }
        criteria.andDeletedEqualTo(false);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public int insertUser(User user) {
        user.setAddTime(LocalDateTime.now());
        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer id) {
        return userMapper.logicalDeleteByPrimaryKey(id);
    }
}
