package com.luv2code.springboot.cruddemo.exceptions;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {

	public InvalidPasswordException() {
		super("Password Mismatch");
	}
	
	public InvalidPasswordException(String string) {
		super(string);
	}

}
