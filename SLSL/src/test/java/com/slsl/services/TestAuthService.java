package com.slsl.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.slsl.configuration.AuthDetailRetrieval;
import com.slsl.models.AuthenticationRequest;
import com.slsl.models.AuthenticationResponse;
import com.slsl.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
class TestAuthService {

	@Mock
	AuthDetailRetrieval repo;

	@Spy
	private AuthenticationManager authenticationManager;

	@Spy
	private JwtUtil jwtUtil;

	@InjectMocks
	AuthService authService;

	@BeforeEach
	private void injectMocks() {
		MockitoAnnotations.initMocks(AuthService.class);
	}

	private AuthService createTestSubject() {
		return authService;
	}

	private AuthenticationRequest createRequest(boolean isGoodRequest) {
		AuthenticationRequest newRequest = new AuthenticationRequest();
		if (isGoodRequest) {
			newRequest.setEmail("test@mail.com");
			newRequest.setPassword("password");
		}
		return newRequest;
	}

	private UserDetails returnUserDetails(boolean isGood) {
		if(isGood) {
			return new User("test@mail.com", "password", new ArrayList<>());
		} else {
			return new User("pfft", "thisIsFalse", new ArrayList<>());
		}
	}

	@Test
	void testLogin() {
		authService = createTestSubject();
		when(repo.loadUserByUsername(ArgumentMatchers.any())).thenReturn(returnUserDetails(true));
		AuthenticationResponse response = authService.login(createRequest(true));
		assertTrue(response.isRegistered());
	}

	@Test
	void testBadRequest() {
		authService = createTestSubject();
		when(repo.loadUserByUsername(ArgumentMatchers.any())).thenThrow(new UsernameNotFoundException(null));
		AuthenticationResponse response = authService.login(createRequest(false));
		assertFalse(response.isRegistered());
		
		
	}
	
	@Test
	void testBadPassword() {
		authService = createTestSubject();
		when(authenticationManager.authenticate(ArgumentMatchers.any())).thenThrow(BadCredentialsException.class);
		AuthenticationRequest badPassword = createRequest(true);
		badPassword.setPassword("totallyWrong");
		AuthenticationResponse response = authService.login(badPassword);
		assertFalse(response.isRegistered());
	}

}
