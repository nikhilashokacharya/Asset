package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.luv2code.springboot.cruddemo.entity.Assets;

public interface AssetsService {
	
	public List<Assets> findAll();
	public Assets findById(int theId);
	public Assets save(Assets theEmployee);
	public void deleteById(int theId);
	
	public Page<Assets> getAssets(int pageNumber,int itemsPerPage);
	public Page<Assets> getSortAssets(int pageNumber,int itemsPerPage, String fieldName);
}
