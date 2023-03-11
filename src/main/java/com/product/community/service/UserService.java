package com.product.community.service;

import java.util.List;

import com.product.community.model.User;

public interface UserService {
	public void createUser(User user);
	public long getStats();
	public List<User> getUsers();
}
