package com.luv2code.springboot.cruddemo.rest;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Assets;
import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;
import com.luv2code.springboot.cruddemo.exceptions.AssetNotFoundException;
import com.luv2code.springboot.cruddemo.exceptions.RequestNotFoundException;
import com.luv2code.springboot.cruddemo.exceptions.UserNotFoundException;
import com.luv2code.springboot.cruddemo.response.Response;
import com.luv2code.springboot.cruddemo.service.RequestsService;
import com.luv2code.springboot.cruddemo.service.UsersService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class RequestsRestController {
	
	@Autowired
	private UsersService usersService;
	
	private RequestsService requestsService;
	@Autowired
	public RequestsRestController(RequestsService theRequestsService) {
		this.requestsService = theRequestsService;
	}
	

	@GetMapping("/approvedrequests")
	public Response<List<RequestForm>> getAllApproved() throws RequestNotFoundException  {
		List<RequestForm> theRequestForm = requestsService.getApproval();

		
		if (theRequestForm != null) {
			return new Response<>(false, "records found", theRequestForm);
		} else {
			return new Response<>(true, "records Not found", theRequestForm);
		}
	}
	

	
	//Approvals
	@PutMapping("/applicationapprove/{appId}")
	public Response<RequestForm> findAllApproved(@PathVariable Integer appId) {
		RequestForm result = requestsService.setApproved(appId);
		if(result == null) {
			return new Response<>(true, "Updation failed", null);
		} else {
			return new Response<>(false, "successfully approved", result);
		}
//		return applicantService.setApproved(appId);
	}
	@PutMapping("/applicationreject/{appId}")
	public Response<RequestForm> findAllRejected(@PathVariable Integer appId) {
		RequestForm result =  requestsService.setRejected(appId);
		if(result == null) {
			return new Response<>(true, "Updation failed", null);
		} else {
			return new Response<>(false, "Rejected ", result);
		}
	}
	
	@GetMapping("/myapprovals/{id}")
	public Response<List<RequestForm>> myApprovals(@PathVariable int id) throws UserNotFoundException {

		List<RequestForm> myRequests= requestsService.myApprovals(id);
		if (myRequests != null) {
			return new Response<>(false, "records found", myRequests);
		} else {
			throw new UserNotFoundException();
		}
	}
	//
	@GetMapping("/requests")
	public Response<List<RequestForm>> getAllRejected() throws RequestNotFoundException  {
		List<RequestForm> theRequestForm = requestsService.getRejected();

		
		if (theRequestForm != null) {
			return new Response<>(false, "records found", theRequestForm);
		} else {
			return new Response<>(true, "records Not found", theRequestForm);
		}
	}
	
//	@GetMapping("/myrequests/{id}")
//	public List<RequestForm> myRequests(@PathVariable int id) {
//
//		return usersService.myRequests(id);
//	}
	
	// add for POST /Assets
	
	@PostMapping("/requests/{id}")
	public Response<RequestForm> addRequestForm(@PathVariable int id,@RequestBody RequestForm theRequestForm) {
//		UsersInfo usersInfo = usersService.findById(id);
//		usersInfo.getUserID();
//		theRequestForm.setRequestID(0);
		
//		theRequestForm.setUserID(usersInfo.getUserID());
		String addRequest= requestsService.addRequest(id, theRequestForm);
 
//		requestsService.save(theRequestForm);
		 if (addRequest != null) {

				return new Response<>(false, addRequest, theRequestForm);
		 }
//			} else {
//				return new Response<>(true, "save failed", null);
//			}
		 return null;
	}
	
//	@PostMapping("/requests/{id}")
//	public Response<RequestForm> addRequestForm1(@PathVariable int id,@RequestBody RequestForm theRequestForm) {
//		UsersInfo usersInfo = usersService.findById(id);
//		theRequestForm.setUserbean(usersInfo);
//		requestsService.save(theRequestForm);
//		if (theRequestForm != null) {
//
//			return new Response<>(false, "added successfully", theRequestForm);
//	 }
//		else {
//			return new Response<>(true, "save failed", null);
//		}
//	
//}
	
	// add mapping for PUT /Assets - update
	
	@PutMapping("/requests/{id}")
	public Response<RequestForm> updateRequestForm(@PathVariable int id,@RequestBody RequestForm theRequestForm) {
		String addRequest= requestsService.addRequest(id, theRequestForm);
		if (addRequest != null) {

			return new Response<>(false, "successfully saved", theRequestForm);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	// add mapping for DELETE /Assets/{assetId}
	@DeleteMapping("/requests/{requestId}")
	public Response<String> deleteRequestForm(@PathVariable int requestId) {
		RequestForm tempRequestForm = requestsService.findById(requestId);
		
		if(tempRequestForm==null) {
			throw new RuntimeException("request id not found : "+requestId);
		}
		requestsService.deleteById(requestId);
		return new Response<>(false, "successfully deleted", null);
		
	}
//	@PutMapping("/applicationapprove/{requestId}")
//	public Response<RequestForm> findAllApproved(@PathVariable Integer requestId) {
//		RequestForm result = requestsService.setAlloted(true);
//		if(result == null) {
//			return new Response<>(true, "Updation failed", null);
//		} else {
//			return new Response<>(false, "successfully updated", result);
//		}
////		return applicantService.setApproved(appId);
//	}
//	@PutMapping("/applicationreject/{appId}")
//	public Response<RequestForm> findAllRejected(@PathVariable Integer appId) {
//		RequestForm result =  requestsService.setAlloted(false);
//		if(result == null) {
//			return new Response<>(true, "Updation failed", null);
//		} else {
//			return new Response<>(false, "successfully updated", result);
//		}
//	}
	
	@GetMapping("/requests/{pageNumber}/{itemsPerPage}")
	public Page<RequestForm> getRequestForm(@PathVariable int pageNumber,@PathVariable int itemsPerPage){
		return requestsService.getRequestForm(pageNumber, itemsPerPage);
	}
	
	@GetMapping("/requests/{pageNumber}/{itemsPerPage}/{fieldName}")
	public Page<RequestForm> getSortRequestForm(@PathVariable int pageNumber,@PathVariable int itemsPerPage,@PathVariable String fieldName){
	return requestsService.getSortRequestForm(pageNumber, itemsPerPage, fieldName);	
	}
	
	
	
}
