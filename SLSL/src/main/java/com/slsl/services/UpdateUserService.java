package com.slsl.services;

import com.slsl.entities.UserModel;
import com.slsl.models.UserUpdateRequest;
import com.slsl.models.UserUpdateResponse;
import com.slsl.repository.UserRepo;

public class UpdateUserService {

	private UserRepo userRepo;
	
	public UpdateUserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public UserUpdateResponse updateUserProfile(UserUpdateRequest request) {
		
		UserModel user = userRepo.findByEmail(request.getEmail()).get(0);
		
	}

}
