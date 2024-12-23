package com.kjy.merchant.filter;

import com.kjy.merchant.common.Code;
import com.kjy.merchant.common.JwtTokenProvider;
import com.kjy.merchant.common.PermitUrl;
import com.kjy.merchant.common.Role;
import com.kjy.merchant.exception.BizException;
import com.kjy.merchant.service.UserDetailService;
import com.kjy.merchant.tenant.TenantContext;
import com.kjy.merchant.util.CookieUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@Component
@Order(2)
public class JwtAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserDetailService userDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	        throws ServletException, IOException {

		String requestURI = request.getRequestURI();
	    String token = jwtTokenProvider.getJwtToken(request);

		if ( token == null ) {
			token = CookieUtils.getJwtFromRequest(request, "token");
		}

	    if (isPermittedURL(requestURI)) {
	        filterChain.doFilter(request, response);
	        return;
	    }

		try {
			if (isValidToken(token)) {
				authenticateUser(token, request);
			} else {
				throw new BizException(Code.TOKEN_EXPIRED, "토큰이 만료되었거나 올바르지 않은 토큰입니다.");
			}
		} catch (BizException e) {
			response.setStatus(801);
			response.getWriter().write(e.getMessage());
			return;
		}
	    
	    filterChain.doFilter(request, response);
	}

	private boolean isPermittedURL(String requestURI) {
	    return Arrays.stream(PermitUrl.PermitUrlList.getPermitURL())
	                 .anyMatch(str -> str.equals(requestURI));
	}

	private boolean isValidToken(String token) {
	    return token != null && jwtTokenProvider.validateToken(token);
	}

	private void authenticateUser(String token, HttpServletRequest request) {
	    String email = jwtTokenProvider.getEmailFromToken(token);
	    UserDetails userDetails = userDetailService.loadUserByUsername(email);
		System.out.println( "권한체크 : >> " +  userDetails.getAuthorities());
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	            userDetails, null, userDetails.getAuthorities());
	    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}



}
