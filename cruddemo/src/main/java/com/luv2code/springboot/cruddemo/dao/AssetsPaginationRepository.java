package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.luv2code.springboot.cruddemo.entity.Assets;

public interface AssetsPaginationRepository extends PagingAndSortingRepository<Assets, Integer> {

}
