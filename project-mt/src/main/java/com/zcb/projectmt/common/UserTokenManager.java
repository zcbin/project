package com.zcb.projectmt.common;


import com.zcb.projectmt.util.JwtUtil;

/**
 * 维护用户token
 */
public class UserTokenManager {
	public static String generateToken(String username) {
        JwtUtil jwtUtil = new JwtUtil();
        return jwtUtil.createToken(username);
    }
    public static String getUserName(String token) {
    	JwtUtil jwtUtil = new JwtUtil();
    	String username = jwtUtil.verifyTokenAndGetUserName(token);
        return username;
    }
}
