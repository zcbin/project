package com.zcb.projectmt.service.impl;

import com.github.pagehelper.PageHelper;
import com.zcb.projectmt.dao.ProjectMapper;
import com.zcb.projectmt.domain.Project;
import com.zcb.projectmt.domain.ProjectExample;
import com.zcb.projectmt.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: zcbin
 * @title: ProjectServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description:
 * @date: 2020/6/3 16:03
 */
@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public int insertProject(Project project) {
        project.setAddTime(LocalDateTime.now());
        project.setUpdateTime(LocalDateTime.now());
        return projectMapper.insertSelective(project);
    }

    @Override
    public int updateProject(Project project) {
        project.setUpdateTime(LocalDateTime.now());
        return projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public int deleteProject(Integer id) {
        return projectMapper.logicalDeleteByPrimaryKey(id);
    }

    @Override
    public Project getProject(Integer id) {
        return projectMapper.selectByPrimaryKeyWithLogicalDelete(id, false);
    }

    @Override
    public List<Project> listProject(String name, Integer offset, Integer limit) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameEqualTo(name);
        }
        criteria.andDeletedEqualTo(false);
        PageHelper.startPage(offset, limit);
        return projectMapper.selectByExample(example);
    }
}
