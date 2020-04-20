package com.slsl.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.RegistrationRequest;
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

	public RegistrationResponse registerUser(RegistrationRequest request) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		RegistrationResponse response = new RegistrationResponse();

		try {
			if (!request.getEmail().isBlank() && !request.getPassword().isBlank()) {
				boolean hasNoDuplicates = duplicateChecker(request);
				if (hasNoDuplicates) {
					user.setFirstName(request.getFirstName());
					user.setLastName(request.getLastName());
					user.setEmail(request.getEmail());
					password.setPassword(encoder.encode(request.getPassword()));
					user.setPassword(password);
					repo.save(user);
					response.setSuccess(true);
				}
			}
		} catch (Exception e) {
			response.setSuccess(false);
		}
		return response;
	}

	private boolean duplicateChecker(RegistrationRequest request) {
		boolean result = false;
		List<UserModel> duplicates= repo.findByEmail(request.getEmail());
		if(duplicates.isEmpty()) {
			result = true;
		}
		return result;
	}
}
