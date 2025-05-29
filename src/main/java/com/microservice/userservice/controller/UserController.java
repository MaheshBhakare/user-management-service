/**
 * 
 */
package com.microservice.userservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.userservice.entity.Users;
import com.microservice.userservice.exceptions.ApiResponse;
import com.microservice.userservice.service.UserService;

/**
 * @author Mahesh Bhakare
 * @version 17
 * @date 29/05/2025
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody Users users) {
		Users user = userService.addUsers(users);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.builder()
					.message("Please fill the data").success(false).status(HttpStatus.NO_CONTENT).build());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@GetMapping
	public ResponseEntity<?> getAllUsers() {
		List<Users> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.builder().message("No users found")
					.success(false).status(HttpStatus.NO_CONTENT).build());
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<Users> getUserByUserId(@PathVariable Integer userId) {
		Users users = userService.getUserByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId) {
		userService.deleteUserById(userId);
		return ResponseEntity.ok(
				ApiResponse.builder().message("User deleted successfully").success(true).status(HttpStatus.OK).build());
	}

}
