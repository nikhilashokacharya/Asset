package com.luv2code.springboot.cruddemo.service;
import java.util.List;

import org.springframework.data.domain.Page;

import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;

public interface UsersService {
	
	public List<UsersInfo> findAll();
	public UsersInfo findById(int id);
	public UsersInfo save(UsersInfo theRequest);
	public void deleteById(int id);
	public UsersInfo findByemail(String email);
	
	public Page<UsersInfo> getRequestForm(int pageNumber,int itemsPerPage);
	public Page<UsersInfo> getSortRequestForm(int pageNumber,int itemsPerPage, String fieldName);
	public List<RequestForm> myRequests(int id);
	public List<RequestForm> myApprovals(int id);
	public String addRequest(int id, UsersInfo theRequestForm);
	UsersInfo update(UsersInfo customer);
	
	
}
