package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.luv2code.springboot.cruddemo.entity.RequestForm;

public interface RequestsPaginationRepository extends PagingAndSortingRepository<RequestForm, Integer> {

}
