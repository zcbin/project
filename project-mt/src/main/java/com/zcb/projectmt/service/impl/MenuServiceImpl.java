package com.zcb.projectmt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zcb.projectmt.dao.MenuMapper;
import com.zcb.projectmt.domain.Menu;
import com.zcb.projectmt.domain.MenuExample;
import com.zcb.projectmt.service.IMenuService;
import com.zcb.projectmt.util.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: zcbin
 * @title: MunuServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description: 菜单服务
 * @date: 2020/6/19 14:32
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public long countMenu(String title) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleEqualTo(title);
        }
        criteria.andDeletedEqualTo(false);
        return menuMapper.countByExample(example);
    }

    @Override
    public Menu getMenu(String title) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        criteria.andTitleEqualTo(title);
        criteria.andDeletedEqualTo(false);
        return menuMapper.selectOneByExample(example);
    }

    @Override
    public List<Menu> listMenu(String title, Integer page, Integer limit) {
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(title)) {
            criteria.andTitleLike("%" + title + "%");
        }
        criteria.andDeletedEqualTo(false);
        PageHelper.startPage(page, limit);
        return menuMapper.selectByExample(example);
    }

    @Override
    public int insertMenu(Menu menu) {
        menu.setAddTime(LocalDateTime.now());
        return menuMapper.insertSelective(menu);
    }

    @Override
    public int updateMenu(Menu menu) {
        if (StringUtils.isEmpty(menu.getId())) {
            return 0;
        }
        menu.setUpdateTime(LocalDateTime.now());
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int deleteMenu(List<Integer> ids) {
        if (EmptyUtil.isEmpty(ids)) {
            return 0;
        }
        MenuExample example = new MenuExample();
        MenuExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        criteria.andDeletedEqualTo(false);
        return menuMapper.logicalDeleteByExample(example);
    }
}
