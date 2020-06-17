package com.zcb.projectmt.controller;

import com.alibaba.fastjson.JSONObject;
import com.zcb.projectmt.common.ErrorCode;
import com.zcb.projectmt.common.UserTokenManager;
import com.zcb.projectmt.domain.User;
import com.zcb.projectmt.service.IUserService;
import com.zcb.projectmt.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zcbin
 * @title: UserController
 * @packageName: com.zcb.projectmt.controller
 * @projectName: project
 * @description:
 * @date: 2020/6/9 19:39
 */
@RestController
@RequestMapping(value = "/mt/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(value = "/login")
    public JSONObject login(@RequestBody String body, HttpServletRequest request) {
        String username = ParseJsonUtil.parseString(body, "username");
        String password = ParseJsonUtil.parseString(body, "password");
        if (username == null || password == null) {
            return ResponseUtil.fail(ErrorCode.PARAMETER_IS_EMPTY, "账号或密码为空");
        }
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = UserTokenManager.generateToken(username);
        return ResponseUtil.ok(token);
    }
    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public JSONObject register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseUtil.fail(ErrorCode.PARAMETER_IS_EMPTY, "账号或密码为空");
        }
        List<User> userList = userService.listUser(user.getUsername());
        if (userList != null && userList.size() > 0) {
            return ResponseUtil.fail(ErrorCode.USERNAME_ALREADY_EXISTS, "账号已存在");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        String password = encoder.encode(user.getPassword());
        // LOGGER.info("********* password==" + password);
        user.setPassword(password); //加密
        user.setStatus((byte) 0); //0可用 1禁用 2注销
        user.setUpdateTime(LocalDateTime.now());
        user.setAddTime(LocalDateTime.now());
        userService.insertUser(user);
        return ResponseUtil.ok();
    }
    @GetMapping(value = "/info")
    public JSONObject info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles", new String[]{"editor"});
        map.put("introduction", "I am an editor");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name", "Normal Editor");
        return ResponseUtil.ok(map);
    }
    @PostMapping(value = "/logout")
    public JSONObject logout() {
        return ResponseUtil.ok();
    }
}
