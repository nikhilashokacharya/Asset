package com.luv2code.springboot.cruddemo.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;

@Data
//@EqualsAndHashCode(callSuper = true, exclude = "userbean")
@Entity
@Table(name="requests")
public class RequestForm {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="request_id")
	private Integer requestID;
	
//	@NotNull(message="Required*")
//	@Pattern(regexp = "^[0-9]+$")
	@Column(name="asset_id")
	private int assetID;
	
//	@NotNull(message="Required*")
//	@Pattern(regexp = "^[0-9]+$")
	@Column(name="employee_id")
	private Integer employeeID;
	
//	@NotNull(message="Required*")
//	@Pattern(regexp = "^[0-9]+$")
	@Column(name="quantity")
	private Integer quantity;
	
//	@NotNull(message="Required*")
	@Column(name="additional_notes")
	private String additionalNotes;
//	@Column
//	private Integer managerID;
	
	@Column(name="alloted")
	private boolean alloted;
	
//	@Column(name="user_id")
//	private int userID;
	
	
	
//	@Exclude
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
    private UsersInfo userbean;
	
//	@OneToMany(mappedBy = "requests", cascade = CascadeType.ALL)
////	@JsonManagedReference
//	private List<Assets> assetRequestList;
	
//	public RequestForm() {
//		
//	}
//
//	public Integer getRequestID() {
//		return requestID;
//	}
//
//	public void setRequestID(Integer requestID) {
//		this.requestID = requestID;
//	}
//
//	public int getAssetID() {
//		return assetID;
//	}
//
//	public void setAssetID(int assetID) {
//		this.assetID = assetID;
//	}
//
//	public Integer getEmployeeID() {
//		return employeeID;
//	}
//
//	public void setEmployeeID(Integer employeeID) {
//		this.employeeID = employeeID;
//	}
//
//	public Integer getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Integer quantity) {
//		this.quantity = quantity;
//	}
//
//	public String getAdditionalNotes() {
//		return additionalNotes;
//	}
//
//	public void setAdditionalNotes(String additionalNotes) {
//		this.additionalNotes = additionalNotes;
//	}
//
//	public boolean isAlloted() {
//		return alloted;
//	}
//
//	public void setAlloted(boolean alloted) {
//		this.alloted = alloted;
//	}
//
//	public UsersInfo getUserbean() {
//		return userbean;
//	}
//
//	public void setUserbean(UsersInfo userbean) {
//		this.userbean = userbean;
//	}
//
//	@Override
//	public String toString() {
//		return "RequestForm [requestID=" + requestID + ", assetID=" + assetID + ", employeeID=" + employeeID
//				+ ", quantity=" + quantity + ", additionalNotes=" + additionalNotes + ", alloted=" + alloted
//				+ ", userbean=" + userbean + "]";
//	}
//	
//	
//	
//	
//
//


}
