package com.slsl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.slsl.beans.RegistrationRequstBean;
import com.slsl.beans.RegistrationResponseBean;
import com.slsl.services.RegistrationService;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RegistrationController {
	
	@Autowired
	public RegistrationController(RegistrationService service) {
		this.registrationService = service;
	}
	private RegistrationService registrationService;
	
	
	@PostMapping(value="/user/register")
	public RegistrationResponseBean registerUser(@RequestBody RegistrationRequstBean request) {
		return registrationService.registerUser(request);
	}
}
