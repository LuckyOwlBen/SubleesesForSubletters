package com.slsl.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {
	
	private static final long serialVersionUID = -1387762954480884467L;
	
	private final String jwt;

	public String getJwt() {
		return jwt;
	}
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
}
