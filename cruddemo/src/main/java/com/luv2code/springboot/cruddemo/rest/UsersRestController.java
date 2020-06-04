package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.config.JwtUtil;
import com.luv2code.springboot.cruddemo.entity.Assets;
import com.luv2code.springboot.cruddemo.entity.RequestForm;
import com.luv2code.springboot.cruddemo.entity.UsersInfo;
import com.luv2code.springboot.cruddemo.exceptions.AssetNotFoundException;
import com.luv2code.springboot.cruddemo.exceptions.UserNotFoundException;
import com.luv2code.springboot.cruddemo.response.Response;
import com.luv2code.springboot.cruddemo.response.TokenResponse;
import com.luv2code.springboot.cruddemo.service.AssetsService;
import com.luv2code.springboot.cruddemo.service.RequestsService;
import com.luv2code.springboot.cruddemo.service.UsersService;

import lombok.extern.java.Log;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
public class UsersRestController {
	
    @Autowired
	private RequestsService requestsService;
	private UsersService usersService;
	@Autowired
	public UsersRestController(UsersService theUsersService) {
		this.usersService = theUsersService;
	}
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@PostMapping("/login")
	public TokenResponse<UsersInfo> login(@RequestBody UsersInfo register) throws Exception{
	
		UsersInfo customer =usersService.findByemail(register.getEmail());
		if(customer == null) {
			return new TokenResponse<UsersInfo>(true, "Invalid Email",null,null);

		}
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(register.getEmail(),register.getPassword()));
		} catch(DisabledException de) {
//			throw new Exception("User disabled",de);

			throw new UserNotFoundException();
			
		} catch(BadCredentialsException bce) {
			
			return new TokenResponse<UsersInfo>(true, "Invalid Password",null,null);

		}// End of try catch
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(register.getEmail());
	
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new TokenResponse<UsersInfo>(false, "Login Successful",jwt,customer);
	}// End of login()
	
	//Requests for Managers
	@GetMapping("/myrequests/{id}")
	public Response<List<RequestForm>> myRequests(@PathVariable int id) throws UserNotFoundException {

		List<RequestForm> myRequests= usersService.myRequests(id);
		if (myRequests != null) {
			return new Response<>(false, "records found", myRequests);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	//Approval for Managers
	@GetMapping("/myapprovals/{id}")
	public Response<List<RequestForm>> myApprovals(@PathVariable int id) throws UserNotFoundException {

		List<RequestForm> myRequests= usersService.myApprovals(id);
		if (myRequests != null) {
			return new Response<>(false, "records found", myRequests);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	//expose "/Assets" and return the list
	@GetMapping("/users")
	public Response<List<UsersInfo>> findAll() throws UserNotFoundException {
		List<UsersInfo> users = usersService.findAll();
		if (users != null) {
			return new Response<>(false, "records found", users);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	// add mapping for GET /Assets/{assetId}
	@GetMapping("/users/{id}")
	public Response<UsersInfo> getRequestForm(@PathVariable int id) throws UserNotFoundException {
		UsersInfo theRequestForm = usersService.findById(id);
		
		if (theRequestForm != null) {
			return new Response<>(false, "records found", theRequestForm);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	// add for POST /Assets
	
	@PostMapping("/users")
	public Response<UsersInfo> addRequestForm(@Valid @RequestBody UsersInfo theRequestForm) {
		
//		theRequestForm.setRequestID(0);
		
		usersService.save(theRequestForm);
		if (theRequestForm != null) {

			return new Response<>(false, "Registered successfully", theRequestForm);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	// add mapping for PUT /Assets - update
	
	@PutMapping("/users/{id}")
	public Response<UsersInfo> updateRequestForm(@PathVariable int id,@RequestBody UsersInfo customer) {
		UsersInfo addRequest= usersService.update(customer);
		
		if (customer != null) {

			return new Response<>(false, "successfully saved", customer);

		} else {
			return new Response<>(true, "save failed", null);
		}
	}
	
	// add mapping for DELETE /Assets/{assetId}
	@DeleteMapping("/users/{id}")
	public Response<String> deleteRequestForm(@PathVariable int id) throws UserNotFoundException {
		UsersInfo tempRequestForm = usersService.findById(id);
		
		if(tempRequestForm==null) {
			throw new UserNotFoundException();
		}
		usersService.deleteById(id);
		return new Response<>(false, "successfully deleted", null);
		
	}
	
//	@PostMapping("/Assets/{email}")
//	public Response<Assets> atm(@PathVariable String email, @RequestBody Assets assets) {
//
//		String assets1 = assetsService.findMyAsset(email, assets);
//
//		if (assets1 != null) {
//			return new Response<Assets>(false, assets1, null);
//		}
//		return null;
//	}
	
	
	@GetMapping("/users/{pageNumber}/{itemsPerPage}")
	public Page<UsersInfo> getRequestForm(@PathVariable int pageNumber,@PathVariable int itemsPerPage){
		return usersService.getRequestForm(pageNumber, itemsPerPage);
	}
	
	@GetMapping("/users/{pageNumber}/{itemsPerPage}/{fieldName}")
	public Page<UsersInfo> getSortRequestForm(@PathVariable int pageNumber,@PathVariable int itemsPerPage,@PathVariable String fieldName){
	return usersService.getSortRequestForm(pageNumber, itemsPerPage, fieldName);	
	}
	
	
	
	
	
	
	
}
