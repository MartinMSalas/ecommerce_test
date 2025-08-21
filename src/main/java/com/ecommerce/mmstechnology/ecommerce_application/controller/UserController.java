package com.ecommerce.mmstechnology.ecommerce_application.controller;


import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserRequestDto;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserResponseDto;
import com.ecommerce.mmstechnology.ecommerce_application.exception.ResourceNotFoundException;
import com.ecommerce.mmstechnology.ecommerce_application.model.User;
import com.ecommerce.mmstechnology.ecommerce_application.service.IUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * UserController.java
 * Project: ecommerce-application, Created by M on 28/7/2025.
 * © 2025 mmstechnology
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	final IUserService userService;

	List<User> userArrayList = new ArrayList<>();

	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable @Min(1) Long id) {
		log.info("Request for fetch User with id: {}", id);

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
	public ResponseEntity<List<UserResponseDto>> getAllUsers(){
		log.info("Request for get all Users");
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto user){
		log.info("Request for create User: {}", user);

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

	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<UserResponseDto> updateUser(
			@PathVariable @Min(1) Long id,
			@Valid @RequestBody UserRequestDto userRequestDto
	) {
		log.info("Request for update User with id: {}", id);

		Optional<UserResponseDto> updated = userService.updateUser(id, userRequestDto);

		return userService.updateUser(id, userRequestDto)
				.map(userResponseDto -> {
					log.debug("User updated sending response entity");
					return new ResponseEntity<>(userResponseDto, HttpStatusCode.valueOf(204));
				})                // 200 OK
				.orElseGet(() -> ResponseEntity.notFound().build()); // 404
	}

	@DeleteMapping("/id")
	public ResponseEntity<UserResponseDto> deleteUser(@PathVariable(name="id") @Min(1) Long userId){
		log.info("Request for delete User with id: {}", userId);


		return userService.deleteUser(userId)
				.map(userResponseDto -> {
					log.debug("User deleted sending response entity");
					return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
				})
				.orElseThrow(()->{
					log.warn("Failed to delete user with id: {}", userId);
					return new ResourceNotFoundException("User not found with ID: " + userId);
				});
	}
}
