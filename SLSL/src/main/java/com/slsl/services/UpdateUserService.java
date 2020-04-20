package com.slsl.services;

import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.slsl.entities.PasswordModel;
import com.slsl.entities.UserModel;
import com.slsl.models.UserUpdateRequest;
import com.slsl.models.UserUpdateResponse;
import com.slsl.repository.UserRepo;
import com.slsl.util.JwtUtil;

@Service
public class UpdateUserService {

	private UserRepo userRepo;
	private JwtUtil jwtUtil;
	
	@Autowired
	public UpdateUserService(UserRepo userRepo, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.jwtUtil = jwtUtil;
	}
	
	public UserUpdateResponse updateUserProfile(UserUpdateRequest request, String auth) {
		
		UserModel user = new UserModel();
		UserUpdateResponse response = new UserUpdateResponse();
		
		try {
			if(jwtUtil.extractUsername(auth).equals(request.getLegacyEmail())) {
				user = userRepo.findByEmail(request.getLegacyEmail()).get(0);
				user.setFirstName(updateColumn(request.getFirstName(), user.getFirstName()));
				user.setLastName(updateColumn(request.getLastName(), user.getLastName()));
				user.setEmail(updateEmail(request.getEmail(), user.getEmail()));
				user.setPassword(updatePassword(request.getPassword(), user.getPassword()));
				user = userRepo.save(user);
			} else {
				throw new AccessDeniedException(auth);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			return response;
		}
		if(!user.equals(null)) {
			response.setFirstName(user.getFirstName());
			response.setLastName(user.getLastName());
			response.setEmail(user.getEmail());
			response.setSuccess(true);
		} else {
			response.setSuccess(false);
		}
		return response;
	}

	private String updateEmail(String newEmail, String oldEmail) throws NonUniqueObjectException {
		
			List<UserModel> duplicates = userRepo.findByEmail(newEmail);
			if(duplicates.isEmpty()) {
				updateColumn(newEmail, oldEmail);
			}
			throw new NonUniqueObjectException(oldEmail, newEmail);
	}

	private PasswordModel updatePassword(String newPassword, PasswordModel oldPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		if(newPassword != null && oldPassword != null) {
			String encPass = encoder.encode(newPassword);
			if(!encPass.equals(oldPassword.getPassword())) {
				oldPassword.setPassword(encPass);
			}
		}
		return oldPassword;
	}

	private String updateColumn(String newString, String oldString) {
		
		if(oldString != null && newString != null) {
			if(!newString.equals(oldString)) {
				return newString;
			}
		}
		return oldString;	
	}
}
