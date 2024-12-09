package com.kjy.merchant.filter;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.common.JwtTokenProvider;
import com.kjy.merchant.common.PermitUrl;
import com.kjy.merchant.common.Role;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.service.UserDetailService;
import com.kjy.merchant.util.CookieUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
	
	private static final String JWT_COOKIE = "JWT-TOKEN";
	private static final String JWT_REFRESH_COOKIE = "RE-JWT-TOKEN";
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailService userDetailService;
	 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

	    String requestURI = request.getRequestURI();
	    String token = CookieUtils.getJwtFromRequest(request, JWT_COOKIE);
	    
	    if (isPermittedURL(requestURI)) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    if (isValidToken(token)) {
	        authenticateUser(token, request);
	    } else {
	        token = CookieUtils.getJwtFromRequest(request, JWT_REFRESH_COOKIE);

	        if (isValidToken(token)) {
	            authenticateUser(token, request);
	            setCookieInToken(response, token);
	        } else {
	            throw new BizException(Code.TOKEN_EXPIRED, requestURI + " 토큰이 만료되었거나 올바르지 않는 토큰입니다.");
	        }
	    }
	    
	    filterChain.doFilter(request, response);
	}

	private boolean isPermittedURL(String requestURI) {
	    return Arrays.stream(PermitUrl.PermitUrlList.getPermitURL())
	                 .anyMatch(str -> str.contains(requestURI));
	}

	private boolean isValidToken(String token) {
	    return token != null && jwtTokenProvider.validateToken(token);
	}

	private void authenticateUser(String token, HttpServletRequest request) {
	    String email = jwtTokenProvider.getEmailFromToken(token);
	    UserDetails userDetails = userDetailService.loadUserByUsername(email);
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	            userDetails, null, userDetails.getAuthorities());
	    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private void setCookieInToken(HttpServletResponse response, String token) {
	    String email = jwtTokenProvider.getEmailFromToken(token);
		Role role = jwtTokenProvider.getRoleFromToken(token);
	    CookieUtils.addJwtRefreshTokenCookie(response, jwtTokenProvider.generateRefreshToken(email, role));
	    CookieUtils.addJwtTokenCookie(response, jwtTokenProvider.generateToken(email, role));
	}


}
