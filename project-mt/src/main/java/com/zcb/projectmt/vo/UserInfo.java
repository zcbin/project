package com.zcb.projectmt.vo;

import java.util.List;

/**
 * @author: zcbin
 * @title: UserInfo
 * @packageName: com.zcb.projectmt.vo
 * @projectName: project
 * @description: 用户信息
 * @date: 2020/6/11 22:12
 */
public class UserInfo {
    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
