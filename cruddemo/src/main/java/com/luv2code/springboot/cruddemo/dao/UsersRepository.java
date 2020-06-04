package com.luv2code.springboot.cruddemo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;
@Repository
public interface UsersRepository extends JpaRepository<UsersInfo, Integer> {

//	@Query("from users where user_id=?1 and password=?2")
//	UsersInfo login(String email, String password);
//	
//	@Query("from users where user_id=?1")
//	boolean search(String email);
	
	@Query("from UsersInfo where email = ?1")
	UsersInfo findByemail(String email); 
}
