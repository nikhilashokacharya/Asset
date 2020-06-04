package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.AssetsRepository;
import com.luv2code.springboot.cruddemo.entity.Assets;

@Service
public class AssetsServiceImpl implements AssetsService {

	private AssetsRepository assetsRepository;
	
	@Autowired
	public AssetsServiceImpl( AssetsRepository theAssetsRepository) {
		assetsRepository = theAssetsRepository;
	}
	@Override
	public List<Assets> findAll() {
		return assetsRepository.findAll();
	}

	@Override
	public Assets findById(int theId) {
		Optional<Assets> result = assetsRepository.findById(theId);
		Assets theEmployee = null;
		if(result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find the employee id : "+theId);
		}
		return theEmployee;
	}

	@Override
	public Assets save(Assets theEmployee) {
		return assetsRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		assetsRepository.deleteById(theId);
	}
	@Override
	public Page<Assets> getAssets(int pageNumber, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return assetsRepository.findAll(pageable);
	}
	@Override
	public Page<Assets> getSortAssets(int pageNumber, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage,Sort.by(fieldName));
		return assetsRepository.findAll(pageable);
	}
}
