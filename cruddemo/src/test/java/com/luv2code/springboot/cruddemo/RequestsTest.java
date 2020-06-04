package com.luv2code.springboot.cruddemo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;
import com.luv2code.springboot.cruddemo.service.RequestsService;
import com.luv2code.springboot.cruddemo.service.UsersService;

@SpringBootTest
 class RequestsTest {

	@Autowired
	private RequestsService requestService;
	
	@Autowired UsersService customerService;
	RequestForm requestForm;
	
	
	
	RequestForm req =null;
	UsersInfo userbean;
	
	
	@BeforeEach
	void addRequest() {
		
		userbean = customerService.findById(1);
		requestForm = new RequestForm();
		
		requestForm.setAssetID(2001);
		requestForm.setEmployeeID(3);
		requestForm.setQuantity(50);
		requestForm.setRequestID(1);
		requestForm.setAdditionalNotes("Testing");
		requestForm.setAlloted(false);
		requestForm.setUserbean(userbean);
		
		req = requestService.save(requestForm);
//		requestForm;
	}
	
	@Test
	void addTest() {
		assertNotNull(req);
	}
		
	@Test
	void findAllTest() {
		List<RequestForm> requests = requestService.findAll();
		assertNotNull(requests);
	}
	
	@AfterEach() 
		void deleteTest() {
		requestForm = requestService.findById(req.getRequestID());
			requestService.deleteById(requestForm.getRequestID());

			
		}
	
	
	
	@Test
	void testAfterDelete() {
		assertNotNull(requestForm);
		
	}
	
	@Test
	void myRequests() {
		int id =1;
		List<RequestForm> benList = userbean.getRequestInfoBeanList1();
		assertNotNull(benList);
	}
	
}
	
	
	
	
	
	
	
		
		
	
