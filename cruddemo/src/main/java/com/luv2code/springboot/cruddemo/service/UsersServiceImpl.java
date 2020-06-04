package com.luv2code.springboot.cruddemo.service;

import java.util.List;






import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.dao.AssetsRepository;
import com.luv2code.springboot.cruddemo.dao.RequestsRepository;
import com.luv2code.springboot.cruddemo.dao.UsersRepository;
import com.luv2code.springboot.cruddemo.entity.Assets;
import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	RequestsRepository requestsRepository;
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersServiceImpl( UsersRepository theUsersRepository) {
		usersRepository = theUsersRepository;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public List<UsersInfo> findAll() {
		return usersRepository.findAll();
	}

	@Override
	public UsersInfo findById(int id) {
		Optional<UsersInfo> result = usersRepository.findById(id);
		UsersInfo theEmployee = null;
		if(result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find the user id : "+id);
		}
		return theEmployee;
	}
	
	@Override
	public UsersInfo findByemail(String email) {
		UsersInfo customer = usersRepository.findByemail(email);
		return customer;

	}

	@Override
	public UsersInfo save(UsersInfo theUsersInfo) {
		theUsersInfo.setPassword(passwordEncoder.encode(theUsersInfo.getPassword()));
		return usersRepository.save(theUsersInfo);
	}

	@Override
	public void deleteById(int id) {
		usersRepository.deleteById(id);
	}
	@Override
	public Page<UsersInfo> getRequestForm(int pageNumber, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return usersRepository.findAll(pageable);
	}
	@Override
	public Page<UsersInfo> getSortRequestForm(int pageNumber, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage,Sort.by(fieldName));
		return usersRepository.findAll(pageable);
	}

	@Override
	public List<RequestForm> myRequests(int id) {

		UsersInfo customer = usersRepository.findById(id).get();
		if (customer != null) {
			return customer.getRequestInfoBeanList1();
		}
		return null;
	}
	List<RequestForm> requestForm3;
	@SuppressWarnings("null")
	@Override
	public List<RequestForm> myApprovals(int id) {
		UsersInfo customer = usersRepository.findById(id).get();
		customer.requestInfoBeanList1=requestsRepository.findAllApproved();
		List<RequestForm> requestForm = customer.getRequestInfoBeanList1();
//		for (RequestForm requestForm2 : requestForm) {
//			if(requestForm2.isAlloted()) {
//				requestForm3.add(requestForm2);
//			} else {
//				return null;
//			}
//		}
//		return requestForm3;
////		customer.getRequestInfoBeanList1().
////		if (customer != null) {
////			 {
////			return customer.getRequestInfoBeanList1();
////			}
////		}
//		
		if (customer != null) {
			return customer.getRequestInfoBeanList1();
		}
		return null;
	}

	@Override
	public String addRequest(int id, UsersInfo theRequestForm) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UsersInfo update(UsersInfo customer) {
		return usersRepository.save(customer);
	}
}
