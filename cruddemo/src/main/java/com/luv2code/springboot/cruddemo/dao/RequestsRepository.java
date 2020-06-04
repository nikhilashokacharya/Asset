package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.RequestForm;
@Repository
public interface RequestsRepository extends JpaRepository<RequestForm, Integer> {
	

	@Query(value = "select a from RequestForm a where alloted = true")
	List<RequestForm> findAllApproved();
	
	@Query(value = "select a from RequestForm a where alloted = false")
	List<RequestForm> findAllRejected();
	
}
