package com.product.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.product.community.model.User;
import com.product.community.repo.UserRepo;
import com.product.community.security.config.UserDetailsImpl;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userInfo=userRepo.findById(username).orElseThrow(()->new UsernameNotFoundException("Invalid User"));
		return new UserDetailsImpl(userInfo.getUserId(),userInfo.getAuthenticationKey());
	}

}
