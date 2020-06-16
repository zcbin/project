package com.zcb.projectmt.filter;

import com.zcb.projectmt.util.ErrorCode;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zcbin
 * @title: AccessDeniedHandler
 * @packageName: com.zcb.projectmt.filter
 * @projectName: project
 * @description: 无权限拦截
 * @date: 2020/6/16 10:58
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(ResponseUtil.fail(ErrorCode.UNAUTHORIZED, "无权限"));
        response.getWriter().flush();
    }
}
