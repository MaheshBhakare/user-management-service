/**
 * 
 */
package com.microservice.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.userservice.dto.AuthRequest;
import com.microservice.userservice.dto.AuthResponse;
import com.microservice.userservice.utility.JwtUtil;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 30/05/2025
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping()
	public ResponseEntity<?> createToken(@RequestBody AuthRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		System.out.println("JWT: " + jwt);
		return ResponseEntity.ok(new AuthResponse(jwt));
	}

}
