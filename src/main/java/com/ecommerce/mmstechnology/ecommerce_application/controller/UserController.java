package com.ecommerce.mmstechnology.ecommerce_application.controller;

import com.ecommerce.mmstechnology.ecommerce_application.model.User;
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
public class UserController {

	List<User> userArrayList = new ArrayList<>();

	@GetMapping("")
	public List<User> getAllUsers(){
		return userArrayList;
	}

	@PostMapping("")
	public List<User> createUser(@RequestBody User user){
		
		return userArrayList;
	}


}
