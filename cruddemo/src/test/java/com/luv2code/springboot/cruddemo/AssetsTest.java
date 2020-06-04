package com.luv2code.springboot.cruddemo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.luv2code.springboot.cruddemo.entity.Assets;
import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;
import com.luv2code.springboot.cruddemo.service.AssetsService;
import com.luv2code.springboot.cruddemo.service.RequestsService;
import com.luv2code.springboot.cruddemo.service.UsersService;

@SpringBootTest
 class AssetsTest {

	@Autowired
	private AssetsService assetsService;
	
	@Autowired UsersService customerService;
	Assets assets;
	
	
	
	Assets req =null;
	UsersInfo userbean;
	
	
	@BeforeEach
	void addAssets() {
		
		userbean = customerService.findById(1);
		assets = new Assets();
		
		assets.setAssetID(2001);
		assets.setAssetCost(200D);
		assets.setAssetName("Car");
		assets.setAssetQuantity(5);
		assets.setAssetDetails("luxury");
		assets.setUserbean(userbean);
		
		req = assetsService.save(assets);
		
	}
	
	@Test
	void addTest() {
		assertNotNull(req);
	}
		
	@Test
	void findAllTest() {
		List<Assets> assets = assetsService.findAll();
		assertNotNull(assets);
	}
	
	@AfterEach() 
		void deleteTest() {
		assets = assetsService.findById(req.getAssetID());
		assetsService.deleteById(assets.getAssetID());

			
		}
	
	
	
	@Test
	void testAfterDelete() {
		assertNotNull(assets);
		
	}
	
	@Test
	void myRequests() {
		int id =1;
		List<Assets> benList = userbean.getAssetBeanList();
		assertNotNull(benList);
	}
	
}
	