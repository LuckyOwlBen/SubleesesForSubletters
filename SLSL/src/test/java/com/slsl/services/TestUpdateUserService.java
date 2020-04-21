package com.slsl.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.UserUpdateRequest;
import com.slsl.models.UserUpdateResponse;
import com.slsl.repository.UserRepo;
import com.slsl.util.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class TestUpdateUserService {
	
	@Mock
	JwtUtil jwtUtil;
	
	@Mock
	UserRepo repo;
	
	@InjectMocks
	UpdateUserService updateService;
	
	private UpdateUserService returnTestInstance() {
		return updateService;
	}
	
	private UserUpdateRequest generateRequest() {
		UserUpdateRequest request = new UserUpdateRequest();
		request.setLegacyEmail("Test@mail.com");
		request.setEmail("bacon@mail.com");
		request.setFirstName("Francis");
		request.setLastName("bacon");
		request.setPassword("unencrypted");
		return request;
	}
	
	private List<UserModel> createUser() {
		List<UserModel> users = new ArrayList<UserModel>();
		UserModel oG = new UserModel(); 
		PasswordModel pG = new PasswordModel();
		oG.setFirstName("Francis"); 
		oG.setLastName("bacon"); 
		oG.setEmail("Test@mail.com");
		pG.setPassword("unencrypted"); 
		oG.setPassword(pG); 
		users.add(oG);
		return users;
	}
	
	@Test
	void testUpdateCurrentUser() {
		updateService = returnTestInstance();
		UserModel updatedUser = createUser().get(0);
		updatedUser.setEmail("bacon@mail.com");
		when(repo.save(ArgumentMatchers.any())).thenReturn(updatedUser);
		when(jwtUtil.extractUsername(ArgumentMatchers.any())).thenReturn("Test@mail.com");
		when(repo.findByEmail("bacon@mail.com")).thenReturn(new ArrayList<>());
		when(repo.findByEmail("Test@mail.com")).thenReturn(createUser());
		UserUpdateResponse response = updateService.updateUserProfile(generateRequest(), "fakeGoodString");
		assertTrue(response.isSuccess());
	}
	
	@Test
	void testBadTokenEmail() {
		updateService = returnTestInstance();
		when(jwtUtil.extractUsername(ArgumentMatchers.any())).thenReturn("not@theRightEmail.com");
		UserUpdateResponse response = updateService.updateUserProfile(generateRequest(), "ThisIsMocked");
		assertFalse(response.isSuccess());
		//Assertions.assertThrows(AccessDeniedException.class, () -> {updateService.updateUserProfile(generateRequest(), "thisIsMocked");});
	}
	
	@Test
	void testBadUserReturn() {
		updateService = returnTestInstance();
		when(jwtUtil.extractUsername(ArgumentMatchers.any())).thenReturn("Test@mail.com");
		when(repo.findByEmail(ArgumentMatchers.any())).thenReturn(new ArrayList<>());
		UserUpdateResponse response = updateService.updateUserProfile(generateRequest(), "fakeGoodString");
		assertFalse(response.isSuccess());
	}

}
