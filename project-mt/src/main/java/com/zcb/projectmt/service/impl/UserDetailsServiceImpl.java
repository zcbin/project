package com.zcb.projectmt.service.impl;

import com.zcb.projectmt.domain.User;
import com.zcb.projectmt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zcbin
 * @title: UserDetailsServiceImpl
 * @packageName: com.zcb.projectmt.service.impl
 * @projectName: project
 * @description:
 * @date: 2020/6/15 17:19
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUser(username);
        if(user == null){
            //throw exception inform front end not this user
            throw new UsernameNotFoundException("user + " + username + "not found.");
        }
        //根据username取角色

        List<String> roleList = new ArrayList<>();//this.userRepository.queryUserOwnedRoleCodes(username);
        roleList.add("ADMIN");
        roleList.add("USER");
        List<GrantedAuthority> authorities = roleList.stream()
                .map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        System.out.println(authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
