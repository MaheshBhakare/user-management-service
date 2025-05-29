/**
 * 
 */
package com.microservice.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.userservice.entity.Users;
import com.microservice.userservice.exceptions.ResourceNotFoundException;
import com.microservice.userservice.repository.UserRepository;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 29/05/2025
 */
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Users addUsers(Users users) {
		if (users != null) {
			String encodedPassword = passwordEncoder.encode(users.getPassword());
			users.setPassword(encodedPassword);
			Users us = userRepository.save(users);
			return us;
		}
		return null;
	}

	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	public Users getUserByUserId(Integer userId) {
		if (userId == null || userId <= 0) {
			throw new IllegalArgumentException("User id Must not be null: " + userId);
		}
		Users user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User with given id is not found: " + userId));

		return user;
	}

	public void deleteUserById(Integer userId) {
		Users user = getUserByUserId(userId);
		userRepository.delete(user);
	}

}
