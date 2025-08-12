package com.ecommerce.mmstechnology.ecommerce_application.controller;


import com.ecommerce.mmstechnology.ecommerce_application.dto.request.UserDtoRequest;
import com.ecommerce.mmstechnology.ecommerce_application.dto.response.UserDtoResponse;
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
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	final IUserService userService;

	List<User> userArrayList = new ArrayList<>();

	@GetMapping("/{id}")
	public ResponseEntity<UserDtoResponse> getUserById(@PathVariable int id) {
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
	public ResponseEntity<List<UserDtoResponse>> getAllUsers(){

		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoRequest user){
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

	@PutMapping(
			value = "/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<UserDtoResponse> updateUser(
			@PathVariable @Min(1) Integer id,
			@Valid @RequestBody UserDtoRequest userDtoRequest
	) {
		log.info("Updating user with id {}", id); // evita loguear el body completo

		Optional<UserDtoResponse> updated = userService.updateUser(id, userDtoRequest);

		return updated.map(ResponseEntity::ok)                // 200 OK con el recurso actualizado
				.orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no existe
	}
}
