package com.luv2code.springboot.cruddemo;

import java.util.Arrays;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.luv2code.springboot.cruddemo.entity.UsersInfo;

@SuppressWarnings("serial")
@Component
public class UserDetailsImpl implements UserDetails {

	private UsersInfo usersInfo;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(usersInfo.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		
		return usersInfo.getPassword();
	}

	@Override
	public String getUsername() {
		
		return usersInfo.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
    
	public UsersInfo getUsersInfo() {
		return usersInfo;
	}
	
	public void setUsersInfo(UsersInfo register) {
		this.usersInfo = register;
	}


}
