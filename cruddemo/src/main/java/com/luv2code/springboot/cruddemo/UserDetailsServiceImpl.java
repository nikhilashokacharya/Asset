package com.luv2code.springboot.cruddemo;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.luv2code.springboot.cruddemo.service.UsersService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsersService registerService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl();
		
		userDetailsImpl.setUsersInfo(registerService.findByemail(username));
		
		return userDetailsImpl;
	}

}
