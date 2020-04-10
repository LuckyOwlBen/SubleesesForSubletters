package com.slsl.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.UserUpdateRequest;
import com.slsl.models.UserUpdateResponse;
import com.slsl.repository.UserRepo;

@Service
public class UpdateUserService {

	private UserRepo userRepo;
	
	public UpdateUserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
	public UserUpdateResponse updateUserProfile(UserUpdateRequest request) {
		
		UserModel user = userRepo.findByEmail(request.getLegacyEmail()).get(0);
		UserUpdateResponse response = new UserUpdateResponse();
		
		try {
			user.setFirstName(updateColumn(request.getFirstName(), user.getFirstName()));
			user.setLastName(updateColumn(request.getLastName(), user.getLastName()));
			user.setEmail(updateColumn(request.getEmail(), user.getEmail()));
			user.setPassword(updatePassword(request.getPassword(), user.getPassword()));
			user = userRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			return response;
		}
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setEmail(user.getEmail());
		response.setSuccess(true);
		return response;
	}

	private PasswordModel updatePassword(String newPassword, PasswordModel oldPassword) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(newPassword != null && oldPassword != null) {
			String encPass = encoder.encode(newPassword);
			if(!encPass.equals(oldPassword.getPassword())) {
				oldPassword.setPassword(encPass);
			}
		} else {
			throw new Exception();
		}
		return oldPassword;
	}

	private String updateColumn(String newString, String oldString) throws Exception {
		
		if(oldString != null && newString != null) {
			if(!newString.equals(oldString)) {
				return newString;
			}
		} else {
			throw new Exception();
		}
		return oldString;	
	}
}
