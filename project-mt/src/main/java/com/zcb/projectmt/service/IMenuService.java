package com.zcb.projectmt.service;

import com.zcb.projectmt.domain.Menu;

import java.util.List;

/**
 * @author: zcbin
 * @title: IMenuService
 * @packageName: com.zcb.projectmt.service
 * @projectName: project
 * @description:
 * @date: 2020/6/19 14:28
 */
public interface IMenuService {
    /**
     * 数量统计
     * @param title
     * @return
     */
    long countMenu(String title);

    /**
     * 获取单个对象
     * @param title
     * @return
     */
    Menu getMenu(String title);

    /**
     * 菜单列表
     * @param title
     * @param page
     * @param limit
     * @return
     */
    List<Menu> listMenu(String title, Integer page, Integer limit);
    int insertMenu(Menu menu);
    int updateMenu(Menu menu);
    int deleteMenu(List<Integer> ids);
}
