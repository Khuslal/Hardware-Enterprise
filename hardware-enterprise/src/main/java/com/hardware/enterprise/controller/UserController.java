package com.hardware.enterprise.controller;

import com.hardware.enterprise.model.User;
import com.hardware.enterprise.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;

	@GetMapping("/users/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.findAll();
	}
	
	@PostMapping("/auth/register")
	public void postUser(@RequestBody User user) {
		userService.save(user);
	}
}
