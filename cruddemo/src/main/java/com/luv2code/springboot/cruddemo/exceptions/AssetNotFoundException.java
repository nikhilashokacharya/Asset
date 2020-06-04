package com.luv2code.springboot.cruddemo.exceptions;

@SuppressWarnings("serial")
public class AssetNotFoundException extends Exception {
	
	public AssetNotFoundException() {
		super("Invalid asset name / price");
	}

}
