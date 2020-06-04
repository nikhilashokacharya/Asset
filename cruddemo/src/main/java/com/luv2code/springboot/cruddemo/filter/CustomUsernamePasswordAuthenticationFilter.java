package com.luv2code.springboot.cruddemo.filter;

import java.io.BufferedReader;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UsersInfo usersInfo;
	
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			usersInfo = null;
			try {
				UsersInfo reg = getUsersInfo(request);
				return reg.getEmail();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainUsername(request);
	}
	
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			
			try {
				UsersInfo reg = getUsersInfo(request);
				return reg.getPassword();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
          return super.obtainPassword(request);
	}
	
	private UsersInfo getUsersInfo(HttpServletRequest request) throws IOException {
		
		if(usersInfo == null) {
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			BufferedReader reader = request.getReader();
			while (reader.ready()) {
				json = json + reader.readLine();
			}
			usersInfo = mapper.readValue(json, UsersInfo.class);
		}
		return usersInfo;
	}
	
	
}

