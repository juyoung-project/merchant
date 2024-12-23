package com.kjy.merchant.util;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;

public class CookieUtils {
	
	private static final int EXPIRATION = 600;
	private static final int REFRESH_EXPIRATION = 7 * 24 * 60 * 60 ;
	public static final String JWT_COOKIE = "token";
	public static final String JWT_REFRESH_COOKIE = "RE-JWT-TOKEN";
	
	
	private CookieUtils() {}
	
	public static void addJwtTokenCookie(HttpServletRequest request, HttpServletResponse response, String token) {
		_addCookie(request, response, token, JWT_COOKIE, EXPIRATION);
	}
	
	public static void addJwtRefreshTokenCookie(HttpServletRequest request, HttpServletResponse response, String token) {
		_addCookie(request, response, token, JWT_REFRESH_COOKIE, REFRESH_EXPIRATION);
	}
	
	private static void _addCookie(HttpServletRequest request, HttpServletResponse response, String token, String cookieType, int expiration) {
		Cookie cookie = new Cookie(cookieType, token);
		cookie.setMaxAge(expiration);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static void clearJwtToken(HttpServletResponse response) {
		Cookie cookie = new Cookie(JWT_COOKIE, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public static String getJwtFromRequest(HttpServletRequest request, String cookieType) {
		String jwtToken = "";
		Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	        	if (cookieType.equals(cookie.getName())) {
	        		jwtToken = cookie.getValue();
	        		break;
	        	}
        	}
	    }
	    return jwtToken;
	 }

	
}
