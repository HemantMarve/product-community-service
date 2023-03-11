package com.product.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.community.model.User;
import com.product.community.repo.UserRepo;
import com.product.community.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void createUser(User user) {
			user.setAuthenticationKey(passwordEncoder.encode(user.getAuthenticationKey()));
			userRepo.save(user);
	}

	@Override
	public long getStats() {
		return userRepo.count();
	}

	@Override
	public List<User> getUsers() {
		return this.userRepo.findAll();
	}

}
