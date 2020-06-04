package com.luv2code.springboot.cruddemo.exceptions;

@SuppressWarnings("serial")
public class QuantityNotAvailableException extends Exception {

	public QuantityNotAvailableException() {
		super("Available quantity lesser than requested");
	}

}
