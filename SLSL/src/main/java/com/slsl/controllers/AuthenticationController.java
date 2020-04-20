package com.slsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slsl.models.AuthenticationRequest;
import com.slsl.models.AuthenticationResponse;
import com.slsl.services.AuthService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthenticationController {

	private AuthService userService;

	@Autowired
	public AuthenticationController(AuthService userService) {

		this.userService = userService;

	}

	@PostMapping(value = "/auth/login")
	public AuthenticationResponse registerUser(@RequestBody AuthenticationRequest request) {
		return userService.login(request);
	}
}
