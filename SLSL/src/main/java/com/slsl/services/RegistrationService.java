package com.slsl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.RegistrationRequst;
import com.slsl.models.RegistrationResponse;
import com.slsl.repository.PasswordRepo;
import com.slsl.repository.UserRepo;

@Service
public class RegistrationService {
	
	@Autowired
	public RegistrationService (UserModel user, PasswordModel password, UserRepo userRepo, PasswordRepo passwordRepo) {
		this.user = user;
		this.password = password;
		this.userRepo = userRepo;
		this.passwordRepo = passwordRepo;
	}
	
	private UserModel user;
	private PasswordModel password;
	private UserRepo userRepo;
	private PasswordRepo passwordRepo;

	public RegistrationResponse registerUser(RegistrationRequst request) {
		RegistrationResponse response = new RegistrationResponse();
		try {
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			password.setPassword(request.getPassword());
			user.setPassword(password);
			userRepo.save(user);
			passwordRepo.save(password);
			response.setSuccess(true);
		} catch(Exception e) {
			response.setSuccess(false);
		}
		return response;
		
	}
}
