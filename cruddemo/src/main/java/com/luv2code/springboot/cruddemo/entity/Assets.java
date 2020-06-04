package com.luv2code.springboot.cruddemo.entity;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Data
@Entity
@Table(name="assets")
public class Assets {

	 @Id
	 @Column(name="asset_id")
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int assetID;
	 
	@NotNull(message="Required*")
    @Column(name="asset_name")
//	@Pattern(regexp = "^[a-zA-Z\\s]*$")
	private String assetName;
	
	@NotNull(message="Required*")
    @Column(name="asset_quantity")
//	@Pattern(regexp = "^[0-9]+$")
	private Integer assetQuantity;
	
	@NotNull(message="Required*")
    @Column(name="asset_cost")
//	@Pattern(regexp = "^[0-9]+$")
	private Double assetCost;
	
   @NotNull(message="Required*")
    @Column(name="asset_details")
	private String assetDetails;
    
    
    @ManyToOne
//    @JsonManagedReference
	@JoinColumn(name = "user_id")
    @JsonBackReference
	private UsersInfo userbean;
    
//    @ManyToOne
////    @JsonBackReference
//	@JoinColumn(name = "request_id")
//	private RequestForm requests;    

}
