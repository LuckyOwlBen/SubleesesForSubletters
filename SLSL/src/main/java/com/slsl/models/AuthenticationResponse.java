package com.slsl.models;

public class AuthenticationResponse {
	
	private final String jwt;
	
	private final boolean registered;
	
	public AuthenticationResponse(String jwt, boolean registered) {
		super();
		this.jwt = jwt;
		this.registered = registered;
	}
	
	public String getJwt() {
		return jwt;
	}
	public boolean isRegistered() {
		return registered;
	}

}
