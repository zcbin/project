package com.zcb.projectmt.service;

import com.zcb.projectmt.domain.Project;

import java.util.List;

/**
 * @author: zcbin
 * @title: IProjectService
 * @packageName: com.zcb.projectmt.service
 * @projectName: project
 * @description:
 * @date: 2020/6/3 11:08
 */
public interface IProjectService {
    /**
     * insert
     * @param project
     * @return
     */
    int insertProject(Project project);

    /**
     * update
     * @param project
     * @return
     */
    int updateProject(Project project);

    /**
     * delete
     * @param id
     * @return
     */
    int deleteProject(Integer id);

    /**
     * 查询
     * @param id
     * @return
     */
    Project getProject(Integer id);

    /**
     * 查询
     * @param name
     * @return
     */
    List<Project> listProject(String name, Integer offset, Integer limit);


}
