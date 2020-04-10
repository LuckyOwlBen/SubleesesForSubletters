package com.slsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slsl.models.AuthenticationRequest;
import com.slsl.models.AuthenticationResponse;
import com.slsl.services.AuthService;
import com.slsl.util.JwtUtil;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class AuthenticationController {
	
	@Autowired
	public AuthenticationController(
			AuthenticationManager authenticationManager, 
			AuthService userService,
			JwtUtil jwtUtil
		) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	private AuthenticationManager authenticationManager;
	private AuthService userService;
	private JwtUtil jwtUtil;

	@PostMapping(value="/authenticate")
	public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody AuthenticationRequest request) {
		try {
				authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
			);
		}
		catch (BadCredentialsException e) {
		}


		final UserDetails userDetails = userService
				.loadUserByUsername(request.getUserName());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
