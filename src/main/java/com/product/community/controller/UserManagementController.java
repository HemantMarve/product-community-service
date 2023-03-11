package com.product.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.community.model.AuthenticationRequest;
import com.product.community.model.JwtResponse;
import com.product.community.model.StandardMessage;
import com.product.community.model.User;
import com.product.community.service.UserService;
import com.product.community.util.JwtTokenUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserManagementController {

	@Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	// returns JWT token if user is authenticated
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody AuthenticationRequest req)  {
		this.authenticate(req);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUser());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	// register new user
	@PostMapping("/register")
	public ResponseEntity<StandardMessage> registerUser(@RequestBody User user) {
		System.out.println(user.getUserId());
		this.userService.createUser(user);
		return ResponseEntity.ok(new StandardMessage("success","user register"));
	}

	@GetMapping("/stats")
	public ResponseEntity<StandardMessage> getStats() {
	        long count=this.userService.getStats();
			return ResponseEntity.ok(new StandardMessage("count of Members",count+""));
	}
	
	@GetMapping("")
	public ResponseEntity<List<User>> getUsers() {
        this.userService.getStats();
		return ResponseEntity.ok(this.userService.getUsers());
     }

	private void authenticate(AuthenticationRequest req) throws UsernameNotFoundException {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(req.getUser(), req.getPassword()));
		} catch (DisabledException e) {
			throw new UsernameNotFoundException("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new UsernameNotFoundException("INVALID_CREDENTIALS", e);
		}
	}

}