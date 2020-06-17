package com.zcb.projectmt.filter;

import com.zcb.projectmt.common.ErrorCode;
import com.zcb.projectmt.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zcbin
 * @title: CustomAuthenticationEntryPoint
 * @packageName: com.zcb.projectmt.filter
 * @projectName: project
 * @description: 未登录拦截
 * @date: 2020/6/16 11:04
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(ResponseUtil.fail(ErrorCode.NOT_LOGGED_IN, "用户未登录或登录过期"));
        response.getWriter().flush();

    }
}
