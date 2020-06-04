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
import com.luv2code.springboot.cruddemo.dao.RequestsRepository;
import com.luv2code.springboot.cruddemo.dao.UsersRepository;
import com.luv2code.springboot.cruddemo.entity.Assets;
import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;

@Service
public class RequestsServiceImpl implements RequestsService {

	@Autowired
	private UsersRepository usersRepository;
	
	private RequestsRepository requestsRepository;
	
	@Autowired
	public RequestsServiceImpl( RequestsRepository theRequestsRepository) {
		requestsRepository = theRequestsRepository;
	}
	@Override
	public List<RequestForm> findAll() {
		return requestsRepository.findAll();
	}
	
	

	@Override
	public RequestForm findById(int theId) {
		Optional<RequestForm> result = requestsRepository.findById(theId);
		RequestForm theEmployee = null;
		if(result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find the request id : "+theId);
		}
		return theEmployee;
	}

	@Override
	public RequestForm save(RequestForm theEmployee) {
		 return requestsRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		requestsRepository.deleteById(theId);
	}
	@Override
	public Page<RequestForm> getRequestForm(int pageNumber, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage);
		return requestsRepository.findAll(pageable);
	}
	@Override
	public Page<RequestForm> getSortRequestForm(int pageNumber, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNumber, itemsPerPage,Sort.by(fieldName));
		return requestsRepository.findAll(pageable);
	}
	@Override
	public List<RequestForm> getApproval() {
		List<RequestForm> requests =  requestsRepository.findAllApproved();
		
			return requests;
		}
	@Override
	public List<RequestForm> getRejected() {
		List<RequestForm> requests =  requestsRepository.findAllRejected();
		
			return requests;
		}
	//For approval
	
	@Override
	public RequestForm setApproved(int id) {
		Optional<RequestForm> result = requestsRepository.findById(id);
		RequestForm applicant = null;
		applicant = result.get();
		applicant.setAlloted(true);
		requestsRepository.save(applicant);
		return applicant;
	}

	@Override
	public RequestForm setRejected(int id) {
		Optional<RequestForm> result = requestsRepository.findById(id);
		RequestForm applicant = null;
		applicant = result.get();
		applicant.setAlloted(false);
		requestsRepository.save(applicant);
		return applicant;
	}
	@Override
	public String addRequest(int id, RequestForm beneficiary) {
		String message = "";
		UsersInfo customer = usersRepository.findById(id).get();

		if (customer == null) {
			message = "User not found";
			return message;
		} else {
			beneficiary.setUserbean(customer);

			requestsRepository.save(beneficiary);
			message = "Request added Successfully";
		}
		return message;
	}
	
}
				
