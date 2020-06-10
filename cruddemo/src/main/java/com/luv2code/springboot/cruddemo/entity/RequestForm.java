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
	
	@NotNull(message="Required*")
	@Column(name="asset_id")
	private int assetID;
	
	@NotNull(message="Required*")
	@Column(name="employee_id")
	private Integer employeeID;
	
	@NotNull(message="Required*")
	@Column(name="quantity")
	private Integer quantity;
	
	@NotNull(message="Required*")
	@Column(name="additional_notes")
	private String additionalNotes;
	
	@Column(name="alloted")
	private boolean alloted;
	
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
    private UsersInfo userbean;
	

}
