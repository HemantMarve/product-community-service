package com.product.community.model;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

	@NotNull
	private String user;
	@NotNull
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
