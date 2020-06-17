package com.zcb.projectmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.domain.Project;
import com.zcb.projectmt.service.IProjectService;
import com.zcb.projectmt.common.ErrorCode;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: zcbin
 * @title: ProjectController
 * @packageName: com.zcb.projectmt.controller
 * @projectName: project
 * @description: 项目
 * @date: 2020/6/3 16:24
 */
@RestController
@RequestMapping(value = "/mt/project")
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @GetMapping(value = "/list")
    public JSONObject list(String name,
                           @RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer limit) {
        List<Project> projectList = projectService.listProject(name, page, limit);
        return ResponseUtil.ok(projectList);
    }

    @PostMapping(value = "/insert")
    public JSONObject insert(@RequestBody Project project) {
        projectService.insertProject(project);
        return ResponseUtil.ok();
    }

    @PostMapping(value = "/update")
    public JSONObject update(@RequestBody Project project) {
        Integer id = project.getId();
        if (id == null) {
            return ResponseUtil.fail(ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
        }
        if (projectService.updateProject(project) == 0) {
            return ResponseUtil.fail(ErrorCode.UPDATE_FAILED, "更新失败");
        }
        return ResponseUtil.ok();
    }

    @PostMapping(value = "/delete")
    public JSONObject delete(@RequestBody Project project) {
       Integer id = project.getId();
       if (id == null) {
           return ResponseUtil.fail(ErrorCode.PARAMETER_IS_EMPTY, "参数错误");
       }
       if (projectService.deleteProject(id) == 0) {
           return ResponseUtil.fail(ErrorCode.DELETE_FAILED, "删除失败");
       }
       return ResponseUtil.ok();
    }

}
