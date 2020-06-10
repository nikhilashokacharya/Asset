package com.luv2code.springboot.cruddemo.entity;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="users")
public class UsersInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userID;
	
	@Email(message="Please enter a valid email")
	@Column(name="email")
	private String email;
	
	@Column(name="user_name")
	@NotNull(message="Required")
	@Size(min=2,message="minimum of two letters required")
	private String userName;
	

	
	@Column(name="role")
	private String role;
	
	@NotNull(message="Required")
	@Column(name="password")
	private String password;

	@OneToMany(mappedBy = "userbean",cascade = CascadeType.ALL)
	@JsonManagedReference
	public List<RequestForm> requestInfoBeanList1;
	
	@OneToMany(mappedBy = "userbean", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Assets> assetBeanList;

	
	
}
