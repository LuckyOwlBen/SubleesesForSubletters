package com.slsl.services;

import java.util.List;

import org.hibernate.NonUniqueObjectException;
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
	
	public UpdateUserService(UserRepo userRepo, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.jwtUtil = jwtUtil;
	}
	
	public UserUpdateResponse updateUserProfile(UserUpdateRequest request, String auth) {
		
		UserModel user = userRepo.findByEmail(request.getLegacyEmail()).get(0);
		UserUpdateResponse response = new UserUpdateResponse();
		
		try {
			if(jwtUtil.extractUsername(auth).equals(request.getLegacyEmail())) {
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
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setEmail(user.getEmail());
		response.setSuccess(true);
		return response;
	}

	private String updateEmail(String newEmail, String oldEmail) throws Exception {
		
			List<UserModel> duplicates = userRepo.findByEmail(newEmail);
			if(duplicates.isEmpty()) {
				updateColumn(newEmail, oldEmail);
			}
			throw new NonUniqueObjectException(oldEmail, newEmail);
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
