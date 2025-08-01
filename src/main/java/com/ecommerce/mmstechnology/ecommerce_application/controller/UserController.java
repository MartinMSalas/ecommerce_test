package com.ecommerce.mmstechnology.ecommerce_application.controller;


import com.ecommerce.mmstechnology.ecommerce_application.exception.ResourceNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import com.ecommerce.mmstechnology.ecommerce_application.service.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.List;

/*
 * UserController.java
 * Project: ecommerce-application, Created by M on 28/7/2025.
 * © 2025 mmstechnology
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	final IUserService userService;

	List<User> userArrayList = new ArrayList<>();

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		log.info("Fetching user with ID: {}", id);

		return userService.getUserById(id)
				.map(user -> {
					log.debug("User found: {}", user);
					return ResponseEntity.ok(user);
				})
				.orElseThrow(() -> {
					log.warn("User with ID {} not found", id);
					return new ResourceNotFoundException("User not found with ID: " + id);
				});
	}


    @GetMapping("")
	public List<User> getAllUsers(){

		return userService.getAllUsers();
	}

	@PostMapping("")
	public ResponseEntity<User> createUser(@RequestBody User user){
		log.info("Creating user: {}", user);

		return userService.createUser(user)
				.map(createdUser -> {
					log.debug("User created: {}", createdUser);
					return ResponseEntity.ok(createdUser);
				})
				.orElseThrow(() -> {
					log.warn("Failed to create user: {}", user);
					return new ResourceNotFoundException("User not found with ID: " + user.getUserId());
		});




	}




}
