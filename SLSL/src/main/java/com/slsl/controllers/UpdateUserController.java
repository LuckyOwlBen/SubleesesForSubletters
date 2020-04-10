package com.slsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slsl.models.UserUpdateRequest;
import com.slsl.models.UserUpdateResponse;
import com.slsl.services.UpdateUserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UpdateUserController {

	private UpdateUserService updateUserService;
	
	@Autowired
	public UpdateUserController(UpdateUserService updateUserService) {
		this.updateUserService = updateUserService;
	}
	
	@PostMapping(value="/user/update")
	public UserUpdateResponse updateUser(@RequestBody UserUpdateRequest request) {
		
		return updateUserService.updateUserProfile(request);
	}
}
