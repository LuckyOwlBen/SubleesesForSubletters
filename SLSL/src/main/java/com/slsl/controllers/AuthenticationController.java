package com.slsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	private AuthenticationManager authenticationManager;
	private AuthService userService;
	private JwtUtil jwtUtil;
	
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

	@PostMapping(value="/auth/login")
	public AuthenticationResponse registerUser(@RequestBody AuthenticationRequest request) {
		UsernamePasswordAuthenticationToken token = null;
		try {
				token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
				authenticationManager.authenticate(token);
		}catch(BadCredentialsException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}


		final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());

		final String jwt = jwtUtil.generateToken(userDetails);

		return new AuthenticationResponse(jwt, true);
	}
}
