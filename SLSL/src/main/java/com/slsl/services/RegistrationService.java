package com.slsl.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.RegistrationRequst;
import com.slsl.models.RegistrationResponse;
import com.slsl.repository.UserRepo;

@Service
public class RegistrationService {
	
	private UserRepo repo;
	private UserModel user = new UserModel();
	private PasswordModel password = new PasswordModel();
	
	public RegistrationService(UserRepo repo) {
		this.repo = repo;
	}

	public RegistrationResponse registerUser(RegistrationRequst request) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		RegistrationResponse response = new RegistrationResponse();
		try {
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			password.setPassword(encoder.encode(request.getPassword()));
			user.setPassword(password);
			repo.save(user);
			response.setSuccess(true);
		} catch(Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
		}
		return response;
		
	}
}
