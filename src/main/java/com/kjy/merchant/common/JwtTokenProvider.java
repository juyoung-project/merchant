package com.kjy.merchant.common;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	private final String TOKEN_PREFIX = "JWT-TOKEN:";
	private final String REFRESH_TOKEN_PREFIX = "RE-JWT-TOKEN:";
	
	public String generateToken(String email, Role role) {
		return this._generateToken(email, role, expiration, TOKEN_PREFIX);
	}
	
    // Refresh Token 생성Role role
    public String generateRefreshToken(String email, Role role) {
        long expirationTime = 1000 * 60 * 60 * 24 * 3; // 3일 유효기간
        return this._generateToken(email, role, expirationTime, REFRESH_TOKEN_PREFIX);
    }
	
    private String _generateToken(String email, Role role, Long expiration, String type) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		claims.put("type", type);
        return Jwts.builder()
                            .setClaims(claims)
                            .setSubject(email)
                            .setIssuedAt(new Date())
                            .setExpiration(new Date(System.currentTimeMillis() + expiration))
                            .signWith(SignatureAlgorithm.HS512, secretKey)
                            .compact();
	}
    
	 // JWT 토큰에서 사용자 정보 추출
    public String getEmailFromToken(String token) {
        
        return this.getBody(token).getSubject();
    }
    
    public Claims getBody(String token) {
    	return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
    
    // JWT 토큰에서 역할(role) 정보 추출
    public Role getRoleFromToken(String token) {
        return Role.valueOf(this.getBody(token).get("role", String.class));
    }

    // JWT 토큰 유효성 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT signature: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Invalid JWT token: " + e.getMessage());
		}
		return false;
	}
}
