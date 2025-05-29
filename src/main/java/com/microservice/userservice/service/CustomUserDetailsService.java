/**
 * 
 */
package com.microservice.userservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.userservice.entity.Users;
import com.microservice.userservice.repository.UserRepository;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 30/05/2025
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				new ArrayList<>());
	}

}
