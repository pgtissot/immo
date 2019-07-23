package com.edu.realestate.exceptions;

public class AuthenticationException extends Exception {

	private static final long serialVersionUID = -1264012926378452206L;

	public AuthenticationException() {
		super("Authenticate");
	}

	public AuthenticationException(String message) {
		super("Authenticate Error : " + message);
	}

	
}
