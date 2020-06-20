package com.zcb.projectmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.common.ErrorCode;
import com.zcb.projectmt.common.ErrorCodeEnum;
import com.zcb.projectmt.domain.Menu;
import com.zcb.projectmt.service.IMenuService;
import com.zcb.projectmt.util.EmptyUtil;
import com.zcb.projectmt.util.ParseJsonUtil;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zcbin
 * @title: MenuController
 * @packageName: com.zcb.projectmt.controller
 * @projectName: project
 * @description:
 * @date: 2020/6/20 14:59
 */
@RestController
@RequestMapping(value = "/mt/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    /**
     * 菜单列表
     * @param title
     * @param page
     * @param limit
     * @return
     */
    @GetMapping(value = "/list")
    public JSONObject list(String title,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "20") Integer limit) {
        List<Menu> menuList = menuService.listMenu(title, page, limit);
        return ResponseUtil.ok(menuList);
    }

    /**
     * 新增
     * @param menu
     * @return
     */
    @PostMapping(value = "/insert")
    public JSONObject insert(@RequestBody Menu menu) {
        String title = menu.getTitle();
        if (EmptyUtil.isEmpty(title)) {
            return ResponseUtil.fail(ErrorCodeEnum.PARAMETER_IS_EMPTY, "标题为空");
        }
        if (menuService.countMenu(title) > 0) {
            return ResponseUtil.fail(ErrorCodeEnum.PARAMETER_IS_REPEAT, "菜单已存在");
        }
        if (menuService.insertMenu(menu) == 0) {
            return ResponseUtil.fail(ErrorCodeEnum.INSERT_FAILED);
        }
        return ResponseUtil.ok();
    }

    /**
     * 修改
     * @param menu
     * @return
     */
    @PostMapping(value = "/update")
    public JSONObject update(@RequestBody Menu menu) {
        if (menuService.updateMenu(menu) == 0) {
            return ResponseUtil.fail(ErrorCodeEnum.UPDATE_FAILED);
        }
        return ResponseUtil.ok();
    }

    /**
     * 删除
     * @param body
     * @return
     */
    @PostMapping(value = "/delete")
    public JSONObject delete(@RequestBody String body) {
        List<Integer> ids = ParseJsonUtil.parseListInteger(body, "ids");
        if (menuService.deleteMenu(ids) == 0) {
            return ResponseUtil.fail(ErrorCodeEnum.DELETE_FAILED);
        }
        return ResponseUtil.ok();
    }

}
