package com.zcb.projectmt.config;

import com.zcb.projectmt.filter.CustomAccessDeniedHandler;
import com.zcb.projectmt.filter.CustomAuthenticationEntryPoint;
import com.zcb.projectmt.filter.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.DigestUtils;
import org.springframework.web.cors.CorsConfiguration;

/**
 * @author: zcbin
 * @title: WebSecurityConfig
 * @packageName: com.zcb.minimallwxapi.config
 * @projectName: minimall
 * @description: Spring security 配置类 所有请求都可访问
 * @date: 2020/5/22 15:22
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //因为使用JWT，所以不需要HttpSession
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //登录接口放行
                .antMatchers("/mt/user/login").permitAll()
                //其他接口全部接受验证
                .anyRequest().authenticated();
                //.and()
                //.addFilter(new JWTAuthenticationFilter(authenticationManager()))



        //使用自定义的 Token过滤器 验证请求的Token是否合法
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // 禁用缓存
        http.headers().cacheControl();
        //添加自定义未授权和未登录结果返回
        http
                .exceptionHandling() //异常处理

                .accessDeniedHandler(accessDeniedHandler) //无权限
                .authenticationEntryPoint(authenticationEntryPoint); //


    }

    @Bean
    public JwtTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtTokenFilter();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}