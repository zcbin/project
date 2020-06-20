package com.zcb.projectmt.config;


import com.zcb.projectmt.common.ErrorCodeEnum;
import com.zcb.projectmt.domain.Permission;
import com.zcb.projectmt.domain.Role;
import com.zcb.projectmt.exception.NotFoundException;
import com.zcb.projectmt.exception.SecurityException;
import com.zcb.projectmt.service.IPermissionService;
import com.zcb.projectmt.service.IRoleService;
import com.zcb.projectmt.vo.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态路由认证
 * </p>
 *
 * @package: com.xkcoding.rbac.security.config
 * @description: 动态路由认证
 * @author: yangkai.shen
 * @date: Created in 2018-12-10 17:17
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
public class RbacAuthorityService {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private RequestMappingHandlerMapping mapping;
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;
        if (userInfo instanceof UserDetails) {
            PrincipalUser principalUser = (PrincipalUser) userInfo;
            Integer userId = principalUser.getId();
            List<Role> roleList = roleService.listRole(userId);
            if (roleList == null) {
                return false;
            }
            List<Integer> roleIds = roleList.stream()
                    .map(Role::getId).collect(Collectors.toList());
            List<Permission> permissionList = permissionService.listPermission(roleIds);
            for (Permission permission : permissionList) {

                AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(permission.getUrl());
                if (antPathRequestMatcher.matches(request)) {
                    hasPermission = true;
                    break;
                }
            }

        }

        return hasPermission;
    }

    /**
     * 校验请求是否存在
     * 404
     * @param request 请求
     */
    private void checkRequest(HttpServletRequest request) {
        // 获取当前 request 的方法
        String currentMethod = request.getMethod();
        Map<String, List<String>> urlMapping = allUrlMapping();

        for (String uri : urlMapping.keySet()) {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request)) {
                if (!urlMapping.get(uri)
                        .contains(currentMethod)) {
                    //throw new SecurityException(Status.HTTP_BAD_METHOD);
                } else {
                    return;
                }
            }
        }
        throw new NotFoundException(ErrorCodeEnum.REQUEST_NOT_FOUND);
    }

    /**
     * 获取 所有URL Mapping，返回格式为{"/test":["GET","POST"],"/sys":["GET","DELETE"]}
     *
     * @return {@link } 格式的 URL Mapping
     */
    private Map<String, List<String>> allUrlMapping() {
        Map<String, List<String>> urlMapping = new HashMap<>();

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((k, v) -> {
            // 获取当前 key 下的获取所有URL
            Set<String> url = k.getPatternsCondition()
                    .getPatterns();
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            //
            // 为每个URL添加所有的请求方法
            url.forEach(s -> urlMapping.put(s, method.getMethods()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.toList())));
        });
        return urlMapping;
    }
}