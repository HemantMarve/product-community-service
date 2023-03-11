package com.product.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user_info")
public class User {

	@Column(name = "user_id")
	@Id
	private String userId;

	@Column(name = "first_name")
	@NotNull
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "authentication_key")
	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String authenticationKey;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAuthenticationKey() {
		return authenticationKey;
	}

	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}

}
