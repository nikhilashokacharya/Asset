package com.luv2code.springboot.cruddemo.exceptions;

@SuppressWarnings("serial")
public class RequestNotFoundException extends Exception {

	public RequestNotFoundException() {
		super("Invalid Request ID");
	}

}
