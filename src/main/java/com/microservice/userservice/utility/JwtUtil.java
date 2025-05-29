/**
 * 
 */
package com.microservice.userservice.utility;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 29/05/2025
 */
@Component
public class JwtUtil {

	private String SECRET_KEY = "secret_key";
	private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

}
