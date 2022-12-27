package com.qa.opencart.exception;

public class FrameworkExceptions extends RuntimeException{

	
	public FrameworkExceptions (String message) {
		super(message);
		printStackTrace();
	}
}
