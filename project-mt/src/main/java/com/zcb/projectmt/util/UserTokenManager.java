package com.zcb.projectmt.util;


/**
 * 维护用户token
 */
public class UserTokenManager {
	public static String generateToken(String username) {
        JwtUtil jwtUtil = new JwtUtil();
        return jwtUtil.createToken(username);
    }
    public static Integer getUserId(String token) {
    	JwtUtil jwtUtil = new JwtUtil();
    	String username = jwtUtil.verifyTokenAndGetUserName(token);

        return 0;
    }
}
