package com.slsl.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.slsl.entities.UserModel;
import com.slsl.models.AuthenticationRequest;
import com.slsl.models.AuthenticationResponse;
import com.slsl.repository.UserRepo;
import com.slsl.util.JwtUtil;

@Service
public class AuthService implements UserDetailsService {

	private AuthenticationManager authenticationManager;
	private UserRepo userRepo;
	private JwtUtil jwtUtil;
	
	@Autowired
	public AuthService (UserRepo userRepo, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.userRepo = userRepo;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
        	List<UserModel> user = userRepo.findByEmail(username);
        	
        	return new User(user.get(0).getEmail(), user.get(0).getPassword().getPassword(), new ArrayList<>());
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new UsernameNotFoundException(e.getMessage());
        }
   }
	
	public AuthenticationResponse login(AuthenticationRequest request) {
		try {	
			authenticateUser(request);
			final UserDetails userDetails = loadUserByUsername(request.getEmail());
			final String jwt = jwtUtil.generateToken(userDetails);
			return new AuthenticationResponse(jwt, true);
		} catch(BadCredentialsException e) {
			return new AuthenticationResponse(null, false);
		}
	}

	private void authenticateUser(AuthenticationRequest request)throws BadCredentialsException  {
		UsernamePasswordAuthenticationToken token = null;
		token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
		authenticationManager.authenticate(token);
	}


	

}
