package com.luv2code.springboot.cruddemo.exceptions;

@SuppressWarnings("serial")
public class InvalidUsernameException extends Exception {
	
	public InvalidUsernameException() {
		super("Name cannot contain numbers or special characters");
	}

}
