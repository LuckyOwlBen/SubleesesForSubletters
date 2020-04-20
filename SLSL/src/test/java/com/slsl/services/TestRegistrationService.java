package com.slsl.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.RegistrationRequest;
import com.slsl.models.RegistrationResponse;
import com.slsl.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
class TestRegistrationService {

	@Mock
	private UserRepo repo;

	@InjectMocks
	private RegistrationService regService;

	public RegistrationService createTestSubject() {
		return regService;
	}

	private RegistrationRequest createRequest() {
		RegistrationRequest request = new RegistrationRequest();
		request.setFirstName("Test");
		request.setLastName("Unit");
		request.setEmail("test@mail.com");
		request.setPassword("testData");
		return request;
	}

	private List<UserModel> createDuplicateUser() {
		List<UserModel> users = new ArrayList<UserModel>();
		UserModel oG = new UserModel(); 
		PasswordModel pG = new PasswordModel();
		oG.setEmail("Test"); 
		oG.setLastName("Unit"); 
		oG.setEmail("test@mail.com");
		pG.setPassword("testData"); 
		oG.setPassword(pG); 
		users.add(oG);
		return users;
	}

	@Test
	void testRegistration() {
		RegistrationService regService = createTestSubject();
		RegistrationRequest request = createRequest();
		RegistrationResponse response = regService.registerUser(request);
		assertTrue(response.getIsSuccess());
	}

	@Test
	void testNullRegistration() {
		RegistrationService regService = createTestSubject();
		RegistrationRequest request = new RegistrationRequest();
		RegistrationResponse response = regService.registerUser(request);
		assertFalse(response.getIsSuccess());
	}
	
	@Before(value = "testDuplicateRegistration()")
	static void setUpBeforeClass() {
		MockitoAnnotations.initMocks(RegistrationService.class);
	}

	@Test
	void testDuplicateRegistration() {
		regService = createTestSubject();
		RegistrationRequest request = createRequest();
		when(repo.findByEmail(ArgumentMatchers.any())).thenReturn(createDuplicateUser());
		RegistrationResponse response = regService.registerUser(request);
		assertFalse(response.getIsSuccess());
	}
}
