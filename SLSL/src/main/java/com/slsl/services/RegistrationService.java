package com.slsl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slsl.beans.RegistrationRequstBean;
import com.slsl.beans.RegistrationResponseBean;
import com.slsl.entities.Password;
import com.slsl.entities.User;
import com.slsl.repository.PasswordRepo;
import com.slsl.repository.UserRepo;

@Service
public class RegistrationService {
	
	@Autowired
	public RegistrationService (User user, Password password, UserRepo userRepo, PasswordRepo passwordRepo) {
		this.user = user;
		this.password = password;
		this.userRepo = userRepo;
		this.passwordRepo = passwordRepo;
	}
	
	private User user;
	private Password password;
	private UserRepo userRepo;
	private PasswordRepo passwordRepo;

	public RegistrationResponseBean registerUser(RegistrationRequstBean request) {
		RegistrationResponseBean response = new RegistrationResponseBean();
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
