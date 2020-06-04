package com.luv2code.springboot.cruddemo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;
import com.luv2code.springboot.cruddemo.service.UsersService;



@SpringBootTest

public class UsersTest {

	@Autowired
	private UsersService usersService;

	UsersInfo usersInfo;
	RequestForm requestInfoBeanList1;
	UsersInfo cus = null;

	@BeforeEach
	void addBeneficiary() {

		usersInfo = new UsersInfo();
//        requestInfoBeanList1.setRequestID(1);
		usersInfo.setEmail("raj@email.com");
		usersInfo.setPassword("qwerty@1234");
		usersInfo.setRole("ROLE_MANAGER");
		usersInfo.setUserID(6);
		usersInfo.setUserName("Raj");

//		usersInfo.setRequestInfoBeanList1(requestInfoBeanList1);
		cus = usersService.save(usersInfo);
//		bene = beneficiaryService.save(beneficiary);

	}

//	@Test
//	void addTest() {
//		assertNotNull(cus);
//	}

//	@Test
//	void findAllTest() {
//		List<Customer> beneficiaries = customerServiceService.findAllCustomers(customer);
//		assertNotNull(beneficiaries);
//	}

	@Test
	void TestSearch() {
		UsersInfo customer1 = usersService.findById(usersInfo.getUserID());
		assertNotNull(customer1);
	}

	@Test
	void getall() {
		List<UsersInfo>customersList = usersService.findAll();
		assertNotNull(customersList);
	}

	@Test
	void getMyRequests() {
		int id = 1;
		List<RequestForm> tranList = usersService.myRequests(id);
		assertNotNull(tranList);
	}
	
	@Test
	void getMyApprovals() {
		int id = 1;
		List<RequestForm> tranList = usersService.myApprovals(id);
		assertNotNull(tranList);
	}
	
}