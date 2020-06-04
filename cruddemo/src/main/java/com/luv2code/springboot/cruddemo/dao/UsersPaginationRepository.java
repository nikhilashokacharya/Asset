package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;

public interface UsersPaginationRepository extends PagingAndSortingRepository<UsersInfo, Integer> {

}
