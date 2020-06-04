package com.luv2code.springboot.cruddemo.service;

import java.util.List;


import org.springframework.data.domain.Page;

import com.luv2code.springboot.cruddemo.entity.RequestForm;

public interface RequestsService {
	
	public List<RequestForm> findAll();
	public RequestForm findById(int theId);
	public RequestForm save(RequestForm theRequest);
	public void deleteById(int theId);
	
	public Page<RequestForm> getRequestForm(int pageNumber,int itemsPerPage);
	public Page<RequestForm> getSortRequestForm(int pageNumber,int itemsPerPage, String fieldName);

	public String addRequest(int id, RequestForm beneficiary);

//	RequestForm setAlloted(boolean b);
	public List<RequestForm> getApproval();
	public List<RequestForm> getRejected();
//	String addRequestwith(int id, RequestForm beneficiary);
	RequestForm setApproved(int id);
	RequestForm setRejected(int id);
	
}
